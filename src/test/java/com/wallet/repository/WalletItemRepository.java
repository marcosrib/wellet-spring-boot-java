package com.wallet.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletItemRepository {

	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);

	private Long savedWalletItemId = null;
	private Long savedWalletId = null;

	@Autowired
	private WalletItemsRepository repository;

	@Autowired
	private WalletRepository walletRepository;

	@BeforeEach
	public void setUp() {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
		savedWalletId = w.getId();

		repository.save(wi);
		savedWalletItemId = wi.getId();
	}

	@AfterEach
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}

	@Test
	public void TestSave() {
		Wallet w = new Wallet();
		w.setName("carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletRepository.save(w);
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		WalletItem response = repository.save(wi);

		assertNotNull(response);
		assertEquals(DATE, response.getDate());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(TYPE, response.getType());
		assertEquals(VALUE, response.getValue());
		assertEquals(response.getWallet().getId(), w.getId());
	}

	@Test
	public void testSaveInvalidWalletItem() throws Exception {
		WalletItem wi = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			repository.save(wi);
		});

	}

	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
		String description = "DEscrição alterada";
		WalletItem changed = wi.get();
		changed.setDescription(description);

		repository.save(changed);

		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);
		assertEquals(description, newWalletItem.get().getDescription());

	}

	@Test
	public void testDelete() {
		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);

		repository.save(wi);
         repository.deleteById(wi.getId());
		Optional<WalletItem> response = repository.findById(wi.getId());
		assertFalse(response.isPresent());

	}
}
