package com.humber.service;

import java.util.List;

import com.humber.common.vo.DataTableVO;
import com.humber.model.Merchant;

public interface MerchantService {

	/**
	 * This service is to get merchant by id.
	 * 
	 * @param id
	 * @return merchant
	 */
	Merchant getMerchantById(String id);

	/**
	 * This service is to get merchant by email id.
	 * 
	 * @param email
	 * @return merchant
	 */
	Merchant getMerchantByEmailId(String email);

	/**
	 * This service is to save merchant.
	 * 
	 * @param merchant
	 * @return merchant
	 */
	Merchant saveMerchant(Merchant merchant);

	/**
	 * This service is to update merchant.
	 * 
	 * @param merchant
	 * @return merchant
	 */
	Merchant updateMerchant(Merchant merchant);

	/**
	 * This service is to delete merchant.
	 * 
	 * @param merchantId
	 * @return isDelete
	 */
	boolean deleteMerchant(String merchantId);

	/**
	 * This service is to get all merchants by pagination and search.
	 * 
	 * @param searchText
	 * @param offset
	 * @param size
	 * @param sortField
	 * @param sortOrder
	 * @return merchants
	 */
	DataTableVO<Merchant> getAllMerchantsByFilter(String searchText, int offset, int size, String sortField,
			int sortOrder);

	/**
	 * This service is to get merchant count.
	 * 
	 * @return count
	 */
	long getTotalCount();

	/**
	 * This service is to get all merchants.
	 * 
	 * @return merchants
	 */
	List<Merchant> getAllMerchants();

}
