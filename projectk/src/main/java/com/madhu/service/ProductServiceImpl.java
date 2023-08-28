package com.madhu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.ProductResponseModel;
import com.madhu.dto.VillageResponseDTO;
import com.madhu.entity.Product;
import com.madhu.entity.SaleRecord;
import com.madhu.entity.Transaction;
import com.madhu.entity.Village;
import com.madhu.exception.ProductException;
import com.madhu.exception.VillageException;
import com.madhu.repository.ProductRepo;
import com.madhu.repository.VillageRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private VillageRepo villageRepo;

	@Autowired
	private CommonUtils utils;

	@Override
	public Product addProduct(Product product) throws ProductException {

		return productRepo.save(product);
	}

	@Override
	public Product getProductByProductId(Integer productId) throws ProductException {

		return productRepo.findById(productId)
				.orElseThrow(() -> new ProductException(Constants.PRODUCT_ID_NOT_FOUND + productId));

	}

	@Override
	public Product updateProduct(Integer productId, Product product) throws ProductException {

		if (!utils.isProductExist(productId))
			throw new ProductException(Constants.PRODUCT_ID_NOT_FOUND + productId);

		return productRepo.save(product);

	}

	@Override
	public Product deleteProduct(Integer productId) throws ProductException {

		Product product = getProductByProductId(productId);

		productRepo.delete(product);

		return product;
	}

	@Override
	public Product getProductByName(String productName) throws ProductException {

		return productRepo.findByProductName(productName)
				.orElseThrow(() -> new ProductException(Constants.PRODUCT_NAME_NOT_FOUND + productName));
	}

	@Override
	public List<ProductResponseModel> getProductByRank() throws VillageException {

		List<ProductResponseModel> productResponseModels = new ArrayList<>();

		List<Product> products = productRepo.findAll();

		Map<Integer, Integer> productSellCount = new HashMap<>();

		Map<Integer, Integer> totalSellingAmount = new HashMap<>();

		Map<Integer, Integer> totalCollectedAmount = new HashMap<>();

		Map<Integer, Integer> villageWiseCount = new HashMap<>();

		for (Product product : products) {

			ProductResponseModel model = new ProductResponseModel();

			Integer productId = product.getProductId();

			for (SaleRecord record : product.getSaleRecords()) {
				productSellCount.put(productId, productSellCount.getOrDefault(productId, 0) + record.getQuantity());

				Integer villageId = record.getAddress().getVillage().getVillageId();

				villageWiseCount.put(villageId, villageWiseCount.getOrDefault(villageId, 0) + record.getQuantity());

				Integer totalRecordsAmount = 0;

				for (Transaction transaction : record.getTransactions())
					totalRecordsAmount += transaction.getAmount();

				totalCollectedAmount.put(productId,
						totalCollectedAmount.getOrDefault(productId, 0) + totalRecordsAmount);

				totalSellingAmount.put(productId,
						totalSellingAmount.getOrDefault(productId, 0) + record.getTotalAmount());
			}

			model.setCollectedAmount(totalCollectedAmount.get(productId));
			model.setProduct(product);
			model.setProductSellCount(productSellCount.get(productId));
			model.setTotalSelledAmount(totalSellingAmount.get(productId));
			model.setPendingAmount(model.getTotalSelledAmount() - model.getCollectedAmount());

			List<VillageResponseDTO> villageDTOs = new ArrayList<>();

			for (Map.Entry<Integer, Integer> e : villageWiseCount.entrySet()) {
				VillageResponseDTO villageDTO = new VillageResponseDTO();

				Village village = villageRepo.findById(e.getKey())
						.orElseThrow(() -> new VillageException(Constants.VILLAGE_ID_NOT_FOUND + e.getKey()));

				villageDTO.setVillageName(village.getVillageName());
				villageDTO.setCount(e.getValue());

				villageDTOs.add(villageDTO);
			}

			model.setVillageWiseCount(villageDTOs);

			productResponseModels.add(model);

		}

		return productResponseModels;
	}

}
