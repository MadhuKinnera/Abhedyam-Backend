package com.madhu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.NameAndId;
import com.madhu.dto.VillageDTO;
import com.madhu.dto.VillageResponseModel;
import com.madhu.entity.Address;
import com.madhu.entity.Customer;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Village;
import com.madhu.exception.AddressException;
import com.madhu.exception.CustomerException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.repository.CustomerRepo;
import com.madhu.repository.VillageRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;
import com.madhu.utils.UserInfo;

import jakarta.annotation.PostConstruct;

@Service
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageRepo villageRepo;

	@Autowired
	private CommonUtils utils;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private UserInfo userInfo;


	@Override
	public Village addVillage(VillageDTO dto) throws VillageException, UserException {

		var village = new Village();

		village.setUser(utils.getUserFromContext());

		village.setVillageName(dto.getVillageName());
		village.setAmountGoal(dto.getAmountGoal());
		if (dto.getDistrict() == null)
			village.setDistrict("Telangana");
		village.setDistrict(dto.getDistrict());
		village.setMandal(dto.getMandal());
		village.setPincode(dto.getPincode());
		village.setProductGoal(dto.getProductGoal());
		village.setState(dto.getState());
		village.setImageUrl(dto.getImageUrl());

		return villageRepo.save(village);
	}

	@Override
	public Village getVillageByVillageId(Integer villageId) throws VillageException {

		return villageRepo.findById(villageId)
				.orElseThrow(() -> new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId));
	}

	@Override
	public Village updateVillage(Integer villageId, Village village) throws VillageException {
		if (!utils.isVillageExist(villageId))
			throw new VillageException(Constants.VILLAGE_ID_NOT_FOUND + villageId);

		village.setVillageId(villageId);

		return villageRepo.save(village);
	}

	@Override
	public Village deleteVillage(Integer villageId) throws VillageException {

		Village village = getVillageByVillageId(villageId);

		villageRepo.delete(village);

		return village;
	}

	@Override
	public List<Village> getVillagesByRank() throws VillageException {

		List<Village> villages = villageRepo.findByUserUserId(userInfo.getUserId());

		System.out.println("the user id is " + userInfo.getUserId());

		if (villages.isEmpty())
			throw new VillageException("No Villages Found ");

		return villages;
	}

	@Override
	public List<Address> getAddressByVillageId(Integer villageId) throws VillageException, AddressException {

		Village village = getVillageByVillageId(villageId);

		if (village.getAddresses().isEmpty())
			throw new AddressException(" No Addresses Found with Village Id " + villageId);

		return village.getAddresses();
	}

	@Override
	public Village getVillageByCustomerName(String customerName) throws CustomerException, VillageException {

		return villageRepo.findTopByAddressesCustomerCustomerNameAndUserUserId(customerName, userInfo.getUserId())
				.orElseThrow(() -> new CustomerException(" Village Not Found with the Customer Name " + customerName));
	}

	@Override
	public List<Village> getVillagesByPincode(Integer pincode) throws VillageException {

		List<Village> villages = villageRepo.findByPincodeAndUserUserId(pincode, userInfo.getUserId());

		if (villages.isEmpty())
			throw new VillageException("Villages Not Found with Pincode " + pincode);

		return villages;

	}

	@Override
	public Village getVillageByCustomerId(Integer customerId) throws CustomerException, VillageException {
		return villageRepo.findByAddressesCustomerCustomerIdAndUserUserId(customerId, userInfo.getUserId())
				.orElseThrow(() -> new CustomerException(" Village Not Found with the Customer Id " + customerId));
	}

	@Override
	public List<VillageResponseModel> getVillageWiseData() throws VillageException {

		var villageWiseData = new ArrayList<VillageResponseModel>();

		var villages = villageRepo.findByUserUserId(userInfo.getUserId());

		System.out.println("The user id is " + userInfo.getUserId());

		if (villages.isEmpty())
			throw new VillageException("Villages Not Found ");

		for (Village v : villages) {

			var villageData = new VillageResponseModel();

			var customers = customerRepo.findByAddressVillageVillageIdAndUserUserId(v.getVillageId(), userInfo.getUserId());

			villageData.setTotalCustomersCount(customers.size());

			var activeCustomers = new ArrayList<Customer>();

			var activeRecords = new ArrayList<SaleRecord>();

			var totalRecords = 0;

			var totalAmount = 0;
			var pendingAmount = 0;

			var totalProductCount = 0;

			for (var c : customers) {

				var records = c.getSaleRecords();

				totalRecords += records.size();

				boolean isActive = false;

				for (var r : records) {

					totalAmount += r.getTotalAmount();

					totalProductCount += r.getQuantity();

					pendingAmount += r.getDueAmount();

					System.out.println("pending amount is " + pendingAmount);

					if (r.getDueAmount() > 0) {
						activeRecords.add(r);
						isActive = true;
					}
				}

				if (isActive)
					activeCustomers.add(c);

			}

			villageData.setVillage(v);

			villageData.setActiveCustomers(activeCustomers);
			villageData.setTotalActiveCustomers(activeCustomers.size());

			villageData.setActiveRecords(activeRecords);
			villageData.setTotalActiveRecords(activeRecords.size());

			villageData.setTotalRecordsCount(totalRecords);
			villageData.setCompletedRecords(totalRecords - activeRecords.size());

			villageData.setTotalAmountFromVillage(totalAmount);
			villageData.setPendingAmount(pendingAmount);
			villageData.setCollectedAmount(totalAmount - pendingAmount);

			villageData.setTotalProductSellCount(totalProductCount);

			villageData.setGoalStatus(
					(v.getAmountGoal() <= totalAmount && v.getProductGoal() <= totalProductCount) ? "COMPLETED"
							: "NOT COMPLETED");

			villageWiseData.add(villageData);

		}

		Collections.sort(villageWiseData,
				(v1, v2) -> Integer.compare(v2.getTotalProductSellCount(), v1.getTotalProductSellCount()));

		return villageWiseData;

	}

	@Override
	public VillageResponseModel getVillageWiseDataByVillageId(Integer villageId) throws VillageException {

		var villageData = new VillageResponseModel();

		var village = villageRepo.findById(villageId)
				.orElseThrow(() -> new VillageException("Village Not Found with Village Id " + villageId));

		var customers = customerRepo.findByAddressVillageVillageIdAndUserUserId(villageId, userInfo.getUserId());

		villageData.setTotalCustomersCount(customers.size());

		var activeCustomers = new ArrayList<Customer>();

		var activeRecords = new ArrayList<SaleRecord>();

		var totalRecords = 0;

		var totalAmount = 0;
		var pendingAmount = 0;

		var totalProductCount = 0;

		for (var c : customers) {

			var records = c.getSaleRecords();

			totalRecords += records.size();

			boolean isActive = false;

			for (var r : records) {

				totalAmount += r.getTotalAmount();

				totalProductCount += r.getQuantity();

				pendingAmount += r.getDueAmount();

				System.out.println("pending amount is " + pendingAmount);

				if (r.getDueAmount() > 0) {
					activeRecords.add(r);
					isActive = true;
				}
			}

			if (isActive)
				activeCustomers.add(c);

		}

		villageData.setVillage(village);

		villageData.setActiveCustomers(activeCustomers);
		villageData.setTotalActiveCustomers(activeCustomers.size());

		villageData.setActiveRecords(activeRecords);
		villageData.setTotalActiveRecords(activeRecords.size());

		villageData.setTotalRecordsCount(totalRecords);
		villageData.setCompletedRecords(totalRecords - activeRecords.size());

		villageData.setTotalAmountFromVillage(totalAmount);
		villageData.setPendingAmount(pendingAmount);
		villageData.setCollectedAmount(totalAmount - pendingAmount);

		villageData.setTotalProductSellCount(totalProductCount);

		villageData.setGoalStatus(
				(village.getAmountGoal() <= totalAmount && village.getProductGoal() <= totalProductCount) ? "COMPLETED"
						: "NOT COMPLETED");

		return villageData;

	}

	@Override
	public List<VillageResponseModel> getVillageWiseDataByVillageNameContaining(String villageName)
			throws VillageException {

		var villageWiseData = new ArrayList<VillageResponseModel>();

		var villages = villageRepo.findByVillageNameContainingAndUserUserId(villageName, userInfo.getUserId());

		System.out.println("The user id is " + userInfo.getUserId());

		if (villages.isEmpty())
			throw new VillageException("Villages Not Found with Name " + villageName);

		for (Village v : villages) {

			var villageData = new VillageResponseModel();

			var customers = customerRepo.findByAddressVillageVillageIdAndUserUserId(v.getVillageId(), userInfo.getUserId());

			villageData.setTotalCustomersCount(customers.size());

			var activeCustomers = new ArrayList<Customer>();

			var activeRecords = new ArrayList<SaleRecord>();

			var totalRecords = 0;

			var totalAmount = 0;
			var pendingAmount = 0;

			var totalProductCount = 0;

			for (var c : customers) {

				var records = c.getSaleRecords();

				totalRecords += records.size();

				boolean isActive = false;

				for (var r : records) {

					totalAmount += r.getTotalAmount();

					totalProductCount += r.getQuantity();

					pendingAmount += r.getDueAmount();

					System.out.println("pending amount is " + pendingAmount);

					if (r.getDueAmount() > 0) {
						activeRecords.add(r);
						isActive = true;
					}
				}

				if (isActive)
					activeCustomers.add(c);

			}

			villageData.setVillage(v);

			villageData.setActiveCustomers(activeCustomers);
			villageData.setTotalActiveCustomers(activeCustomers.size());

			villageData.setActiveRecords(activeRecords);
			villageData.setTotalActiveRecords(activeRecords.size());

			villageData.setTotalRecordsCount(totalRecords);
			villageData.setCompletedRecords(totalRecords - activeRecords.size());

			villageData.setTotalAmountFromVillage(totalAmount);
			villageData.setPendingAmount(pendingAmount);
			villageData.setCollectedAmount(totalAmount - pendingAmount);

			villageData.setTotalProductSellCount(totalProductCount);

			villageData.setGoalStatus(
					(v.getAmountGoal() <= totalAmount && v.getProductGoal() <= totalProductCount) ? "COMPLETED"
							: "NOT COMPLETED");

			villageWiseData.add(villageData);

		}

		return villageWiseData;

	}

	@Override
	public List<NameAndId> getVillageNames() throws VillageException {
		var villages = getVillagesByRank();

		if (villages.isEmpty())
			throw new VillageException("Villages Not Found");

		return villages.stream().map(v -> new NameAndId(v.getVillageName(), v.getVillageId()))
				.collect(Collectors.toList());

	}

}
