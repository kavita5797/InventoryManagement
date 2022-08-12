package com.humber.service;

import com.humber.common.vo.DataTableVO;
import com.humber.model.Merchant;

public interface MerchantService {

	Merchant getMerchantById(String id);

	Merchant getMerchantByEmailId(String email);

	Merchant saveMerchant(Merchant merchant);

	Merchant updateMerchant(Merchant merchant);

	boolean deleteMerchant(String merchantId);

	DataTableVO<Merchant> getAllMerchantsByFilter(String searchText, int offset, int size, String sortField,
			int sortOrder);

	long getTotalCount();

}
