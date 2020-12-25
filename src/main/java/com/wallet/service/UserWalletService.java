package com.wallet.service;

import org.springframework.stereotype.Service;

import com.wallet.entity.UserWallet;

@Service
public interface UserWalletService {
	UserWallet save(UserWallet userWallet);
}
