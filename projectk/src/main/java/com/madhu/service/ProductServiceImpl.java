package com.madhu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhu.dto.NameAndId;
import com.madhu.dto.ProductDTO;
import com.madhu.dto.ProductResponseModel;
import com.madhu.dto.VillageResponseDTO;
import com.madhu.entity.Product;
import com.madhu.exception.ProductException;
import com.madhu.exception.UserException;
import com.madhu.exception.VillageException;
import com.madhu.repository.ProductRepo;
import com.madhu.utils.CommonUtils;
import com.madhu.utils.Constants;
import com.madhu.utils.UserInfo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CommonUtils utils;
	
	@Autowired
	private UserInfo userInfo;

	@Override
	public Product addProduct(ProductDTO dto) throws ProductException, UserException {

		var product = new Product();

		product.setBuyedPrice(dto.getBuyedPrice());
		product.setDescription(dto.getDescription());
		product.setSellingPrice(dto.getSellingPrice());
		product.setProductName(dto.getProductName());
		product.setImageUrl(dto.getImageUrl());
		

		product.setUser(utils.getUserFromContext());

		return productRepo.save(product);
	}

	@Override
	public Product getProductByProductId(Integer productId) throws ProductException {

		return productRepo.findByProductIdAndUserUserId(productId, userInfo.getUserId())
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

		return productRepo.findByProductNameAndUserUserId(productName, userInfo.getUserId())
				.orElseThrow(() -> new ProductException(Constants.PRODUCT_NAME_NOT_FOUND + productName));
	}

	@Override
	public List<ProductResponseModel> getProductResponseModels() throws VillageException, ProductException {

		List<ProductResponseModel> productResponseModels = new ArrayList<>();

		List<Product> products = productRepo.findByUserUserId(userInfo.getUserId());
		
		System.out.println(products.size()+" Products Found and user id "+userInfo.getUserId());

		if (products.isEmpty())
			throw new ProductException(Constants.NO_PRODUCTS_FOUND);

		for (var p : products) {

			var productResponse = new ProductResponseModel();

			productResponse.setProduct(p);

			int productSellCount = 0;

			int totalSelledAmount = 0;

			int totalPendingAmount = 0;

			Map<String, Integer> villageMap = new HashMap<String, Integer>();

			for (var r : p.getSaleRecords()) {
				productSellCount += r.getQuantity();
				totalSelledAmount += r.getTotalAmount();
				totalPendingAmount += r.getDueAmount();

				var villageName = r.getCustomer().getAddress().getVillage().getVillageName();

				var productQuantity = r.getQuantity();

				villageMap.put(villageName, villageMap.getOrDefault(villageName, 0) + productQuantity);
			}

			productResponse.setPendingAmount(totalPendingAmount);
			productResponse.setTotalSelledAmount(totalSelledAmount);
			productResponse.setCollectedAmount(totalSelledAmount - totalPendingAmount);
			productResponse.setProductSellCount(productSellCount);

			var villageWiseDto = new ArrayList<VillageResponseDTO>();

			for (Map.Entry<String, Integer> villageWiseMap : villageMap.entrySet()) {

				var villageDto = new VillageResponseDTO();

				villageDto.setVillageName(villageWiseMap.getKey());
				villageDto.setCount(villageWiseMap.getValue());

				villageWiseDto.add(villageDto);
			}

			productResponse.setVillageWiseCount(villageWiseDto);

			productResponseModels.add(productResponse);

		}

		Collections.sort(productResponseModels,
				(p1, p2) -> Integer.compare(p2.getProductSellCount(), p1.getProductSellCount()));

		return productResponseModels;
	}

	@Override
	public Product uploadProductImage(Integer productId, String productFile) throws ProductException, IOException {

		Product product = getProductByProductId(productId);

		String productImageURL = productFile;// utils.convertImageToUrl(productFile);

		product.setImageUrl(productImageURL);

		return productRepo.save(product);

	}

	@Override
	public ProductResponseModel getProductResponseModelByProductId(Integer productId)
			throws VillageException, ProductException {

		Product product = productRepo.findByProductIdAndUserUserId(productId, userInfo.getUserId())
				.orElseThrow(() -> new ProductException("Product Not Found with Product Id " + productId));

		var productResponse = new ProductResponseModel();

		productResponse.setProduct(product);

		int productSellCount = 0;

		int totalSelledAmount = 0;

		int totalPendingAmount = 0;

		Map<String, Integer> villageMap = new HashMap<String, Integer>();

		for (var r : product.getSaleRecords()) {
			productSellCount += r.getQuantity();
			totalSelledAmount += r.getTotalAmount();
			totalPendingAmount += r.getDueAmount();

			var villageName = r.getCustomer().getAddress().getVillage().getVillageName();

			var productQuantity = r.getQuantity();

			villageMap.put(villageName, villageMap.getOrDefault(villageName, 0) + productQuantity);
		}

		productResponse.setPendingAmount(totalPendingAmount);
		productResponse.setTotalSelledAmount(totalSelledAmount);
		productResponse.setCollectedAmount(totalSelledAmount - totalPendingAmount);
		productResponse.setProductSellCount(productSellCount);

		var villageWiseDto = new ArrayList<VillageResponseDTO>();

		for (Map.Entry<String, Integer> villageWiseMap : villageMap.entrySet()) {

			var villageDto = new VillageResponseDTO();

			villageDto.setVillageName(villageWiseMap.getKey());
			villageDto.setCount(villageWiseMap.getValue());

			villageWiseDto.add(villageDto);
		}

		productResponse.setVillageWiseCount(villageWiseDto);

		return productResponse;

	}

	@Override
	public List<Product> getProducts() throws ProductException {

		List<Product> products = productRepo.findByUserUserId(userInfo.getUserId());

		if (products.isEmpty())
			throw new ProductException("No Products Found ");

		return products;
	}

	@Override
	public List<ProductResponseModel> getProductsContainingProductName(String productName) throws ProductException {

		List<ProductResponseModel> productResponseModels = new ArrayList<>();

		List<Product> products = productRepo.findByProductNameContainingAndUserUserId(productName, userInfo.getUserId());

		if (products.isEmpty())
			throw new ProductException("Products Not Found with Name " + productName);

		for (var p : products) {

			var productResponse = new ProductResponseModel();

			productResponse.setProduct(p);

			int productSellCount = 0;

			int totalSelledAmount = 0;

			int totalPendingAmount = 0;

			Map<String, Integer> villageMap = new HashMap<String, Integer>();

			for (var r : p.getSaleRecords()) {
				productSellCount += r.getQuantity();
				totalSelledAmount += r.getTotalAmount();
				totalPendingAmount += r.getDueAmount();

				var villageName = r.getCustomer().getAddress().getVillage().getVillageName();

				var productQuantity = r.getQuantity();

				villageMap.put(villageName, villageMap.getOrDefault(villageName, 0) + productQuantity);
			}

			productResponse.setPendingAmount(totalPendingAmount);
			productResponse.setTotalSelledAmount(totalSelledAmount);
			productResponse.setCollectedAmount(totalSelledAmount - totalPendingAmount);
			productResponse.setProductSellCount(productSellCount);

			var villageWiseDto = new ArrayList<VillageResponseDTO>();

			for (Map.Entry<String, Integer> villageWiseMap : villageMap.entrySet()) {

				var villageDto = new VillageResponseDTO();

				villageDto.setVillageName(villageWiseMap.getKey());
				villageDto.setCount(villageWiseMap.getValue());

				villageWiseDto.add(villageDto);
			}

			productResponse.setVillageWiseCount(villageWiseDto);

			productResponseModels.add(productResponse);

		}

		return productResponseModels;
	}

	@Override
	public List<NameAndId> getProductsName() throws ProductException {

		var products = getProducts();

		if (products.isEmpty())
			throw new ProductException("Products Not Found");

		return products.stream().map(p -> new NameAndId(p.getProductName(), p.getProductId()))
				.collect(Collectors.toList());
	}

}
