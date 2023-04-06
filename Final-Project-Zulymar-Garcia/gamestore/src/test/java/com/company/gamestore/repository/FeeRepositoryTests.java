package com.company.gamestore.repository;

import com.company.gamestore.models.Fee;
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
public class FeeRepositoryTests {

    @Autowired
    FeeRepository feeRepository;

    @Before
    public void setUp() throws Exception {

        feeRepository.deleteAll();
    }


    @Test
    public void shouldfindByProductType() {

        Fee fee = new Fee();
        fee.setProductType("Consoles");
        fee.setFee(BigDecimal.valueOf(14.99));
        fee = feeRepository.save(fee);

        Fee fee2 = new Fee();
        fee2.setProductType("Games");
        fee2.setFee(BigDecimal.valueOf(1.49));
        fee2 = feeRepository.save(fee2);

        Fee foundFee = feeRepository.findByProductType(fee.getProductType());

        assertEquals(foundFee, fee);
    }


}