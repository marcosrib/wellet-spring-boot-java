package com.wallet.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@NoArgsConstructor
@Data
public class WalletDTO {
	private Long id;
	@Length(min=3)
	@NonNull
	private String name;
	@NonNull
	private BigDecimal value;
}
