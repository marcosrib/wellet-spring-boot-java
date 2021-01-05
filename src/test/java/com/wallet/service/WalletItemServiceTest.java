package com.wallet.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.enums.TypeEnum;
import com.wallet.repository.WalletItemsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemServiceTest {

	@MockBean
	WalletItemsRepository repository;
	
	@Autowired
	WalletItemService service;
	
	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	
	@Test
	public void testSave() {
	 BDDMockito.given(repository.save(Mockito.any(WalletItem.class))).willReturn(getMockWalletItem());
	 
	 WalletItem response = service.save(new WalletItem());
	 
	 assertNotNull(response);
	 assertEquals(response.getDescription(), DESCRIPTION);
	 assertEquals(response.getValue().compareTo(VALUE), 0);
	}

	@Test
	public void testFindBetweenDates() {
		
	 List<WalletItem> list = new ArrayList<>();
	 list.add(getMockWalletItem());
	 
	 Page<WalletItem> page = new PageImpl<WalletItem>(list); 
	 BDDMockito.given(repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Mockito.anyLong(), Mockito.any(Date.class), Mockito.any(Date.class), Mockito.any(Pageable.class))).willReturn(page);
	 Page<WalletItem> response =  service.findBetweenDates(1L, new Date(), new Date(), 0);

	 
	 assertNotNull(response);
	 assertEquals(response.getContent().size(), 1);
	 assertEquals(response.getContent().get(0).getDescription(), DESCRIPTION);
	}

	
	
	private WalletItem getMockWalletItem() {
		Wallet w  = new Wallet();
		w.setId(1L);
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		return wi;
	}
}
