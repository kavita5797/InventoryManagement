package com.humber.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humber.common.vo.DataTableVO;
import com.humber.model.Merchant;
import com.humber.repository.MerchantRepository;
import com.humber.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MerchantRepository merchantRepository;

	@Override
	public Merchant getMerchantById(String id) {
		Optional<Merchant> merchant = merchantRepository.findById(id);
		if (merchant.isPresent()) {
			logger.info("Merchant is present::" + merchant.toString());
			return merchant.get();
		}
		return null;
	}

	@Override
	public Merchant getMerchantByEmailId(String email) {
		logger.info("find merchand by email ::" + email);
		return merchantRepository.findByEmail(email);
	}

	@Override
	public Merchant saveMerchant(Merchant merchant) {
		merchant.setId(UUID.randomUUID().toString());
		merchant = merchantRepository.save(merchant);
		logger.info("Merchant created::" + merchant.toString());
		return merchant;
	}

	@Override
	public Merchant updateMerchant(Merchant merchant) {
		Merchant oldMerchant = getMerchantById(merchant.getId());
		if (oldMerchant != null) {
			oldMerchant.setName(merchant.getName());
			oldMerchant.setPhone(merchant.getPhone());
			oldMerchant.setEmail(merchant.getEmail());
			oldMerchant.setAddress(merchant.getAddress());
			oldMerchant.setCountry(merchant.getCountry());
			merchant = merchantRepository.save(oldMerchant);
			logger.info("Merchant updated::" + merchant.toString());
			return merchant;
		}
		return null;
	}

	@Override
	public boolean deleteMerchant(String merchantId) {
		if (getMerchantById(merchantId) != null) {
			merchantRepository.deleteById(merchantId);
			logger.info("Merchant deleted::" + merchantId);
			return true;
		}
		return false;
	}

	@Override
	public DataTableVO<Merchant> getAllMerchantsByFilter(String searchText, int offset, int size, String sortField,
			int sortOrder) {
		Direction direction = sortOrder == 1 ? Direction.ASC : Direction.DESC;
		Sort sort = Sort.by(direction, sortField);
		Pageable pageable = PageRequest.of(offset, size, sort);
		Page<Merchant> merchantList = null;

		DataTableVO<Merchant> merchantData = new DataTableVO<>();

		merchantData.setFirst(pageable.getPageNumber());
		merchantData.setRows(pageable.getPageSize());
		if (searchText != null && searchText != "") {
			merchantList = merchantRepository.findByEmailIgnoreCaseContainsOrNameIgnoreCaseContains(searchText,
					searchText, pageable);
		} else {
			merchantList = merchantRepository.findAll(pageable);
		}

		merchantData.setResult(merchantList.getContent());
		merchantData.setTotalRecords((int) merchantList.getTotalElements());
		return merchantData;
	}

	@Override
	public long getTotalCount() {
		return merchantRepository.count();
	}

}
