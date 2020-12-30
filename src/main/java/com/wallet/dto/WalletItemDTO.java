package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class WalletItemDTO {
	private Long id;
	private Long wallet;
	private Date date;
	private String type;
	private String description;
	private BigDecimal value;
}
