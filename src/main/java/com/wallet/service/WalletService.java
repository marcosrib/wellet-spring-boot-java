package com.wallet.service;

import org.springframework.stereotype.Service;

import com.wallet.entity.Wallet;

@Service
public interface WalletService {
	Wallet save(Wallet wallet);
}
