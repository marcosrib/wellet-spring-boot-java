package com.wallet.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wallet.entity.WalletItem;
import com.wallet.enums.TypeEnum;

public interface WalletItemsRepository extends JpaRepository<WalletItem, Long> {

	Page<WalletItem> findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long savedWalletId, Date date,
			Date currentDatePlusFiveDays, Pageable page);

	List<WalletItem> findByWalletIdAndType(Long savedWalletId, TypeEnum type);
    
	@Query(value= "select sum(value) from WalletItem wi where wi.wallet.id = :wallet")
	BigDecimal sumByWalletId(@Param("wallet") Long wallet);

}
