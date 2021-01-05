package com.wallet.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemsRepository;
import com.wallet.service.WalletItemService;

@Service
public class WalletItemServiceImpl implements WalletItemService {
    @Autowired
	private WalletItemsRepository repository;

	@Override
	public WalletItem save(WalletItem walletItem) {
		return repository.save(walletItem);
	}

	@Override
	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
		Pageable pg = PageRequest.of(page, 10);
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pg);
	}

}
