package com.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wallet.entity.WalletItem;
import com.wallet.enums.TypeEnum;
@Service
public interface WalletItemService {

	WalletItem save(WalletItem walletItem);

	Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

	List<WalletItem> findByWalletAnType(Long wallet, TypeEnum types);

	BigDecimal sumByWalletId(Long wallet);

}
