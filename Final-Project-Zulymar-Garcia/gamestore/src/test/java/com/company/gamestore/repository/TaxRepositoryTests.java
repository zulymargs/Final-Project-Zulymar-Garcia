package com.company.gamestore.repository;

import com.company.gamestore.models.Tax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaxRepositoryTests {

    @Autowired
    TaxRepository taxRepository;

    @Before
    public void setUp() throws Exception {

        taxRepository.deleteAll();
    }

    @Test
    public void shouldfindByState() {

        Tax tax = new Tax();
        tax.setState("CA");
        tax.setFee(BigDecimal.valueOf(0.06));
        tax = taxRepository.save(tax);

        Tax tax2 = new Tax();
        tax2.setState("AL");
        tax2.setFee(BigDecimal.valueOf(0.05));
        tax2 = taxRepository.save(tax2);

        Tax foundTax = taxRepository.findByState(tax.getState());

        assertEquals(foundTax, tax);
    }

}