package com.company.gamestore.repository;
import com.company.gamestore.repository.TShirtRepository;
import com.company.gamestore.models.Console;
import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTests {
    @Autowired
    InvoiceRepository invoiceRepo;
/*
    @Autowired
    TShirtRepository tshirtRepo;

    @Autowired
    GameRepository gameRepo;

    @Autowired
    ConsoleRepository consoleRepo;

    */

    @Before
    public void setUp() throws Exception{
        invoiceRepo.deleteAll();
//        tshirtRepo.deleteAll();
//        gameRepo.deleteAll();
//        consoleRepo.deleteAll();
    }


    @Test
    public void shouldCreateInvoice(){

        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoiceRepo.save(invoice);

        invoice = invoiceRepo.save(invoice);

        //Assert...
        Optional<Invoice> invoice1 = invoiceRepo.findById(invoice.getId());

        assertEquals(invoice1.get(), invoice);


    }

    @Test
    public void getInvoiceByCustomerName(){


        Invoice invoice2 = new Invoice();

        invoice2.setItem_id(12345);
        invoice2.setName("John Doe");
        invoice2.setStreet("234 Mack Lane");
        invoice2.setItem_type("TShirt");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("23456");
        invoice2.setQuantity(2);
        invoice2.setUnit_price(BigDecimal.valueOf(20.99));
        invoice2.setSubtotal(BigDecimal.valueOf(41.98));
        invoice2.setTax(BigDecimal.valueOf(0.07));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice2.setTotal(BigDecimal.valueOf(46.90));

        invoiceRepo.save(invoice2);

        Invoice invoice3 = new Invoice();

        invoice3.setItem_id(12345);
        invoice3.setName("John Doe");
        invoice3.setStreet("234 Mack Lane");
        invoice3.setItem_type("Game");
        invoice3.setCity("Atlanta");
        invoice3.setState("GA");
        invoice3.setZipcode("23456");
        invoice3.setQuantity(2);
        invoice3.setUnit_price(BigDecimal.valueOf(20.99));
        invoice3.setSubtotal(BigDecimal.valueOf(41.98));
        invoice3.setTax(BigDecimal.valueOf(0.07));
        invoice3.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice3.setTotal(BigDecimal.valueOf(46.90));
        invoiceRepo.save(invoice3);


        List<Invoice> tList = invoiceRepo.findByName("John Doe");
        assertEquals(tList.size(), 2);


    }

    @Test
    public void getAllInvoices(){
/*
        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);

 */
/*

        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);
        */


        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoiceRepo.save(invoice);

        Invoice invoice2 = new Invoice();

        invoice2.setItem_id(12345);
        invoice2.setItem_type("T-shirt");
        invoice2.setName("Jane Doe");
        invoice2.setStreet("234 Mack Lane");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("23456");
        invoice2.setQuantity(2);
        invoice2.setUnit_price(BigDecimal.valueOf(20.99));
        invoice2.setSubtotal(BigDecimal.valueOf(41.98));
        invoice2.setTax(BigDecimal.valueOf(0.07));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice2.setTotal(BigDecimal.valueOf(46.90));

        invoiceRepo.save(invoice2);

        List<Invoice> i_List = invoiceRepo.findAll();
        assertEquals(i_List.size(), 2);

    }

    @Test
    public void getInvoiceById(){
/*
        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);


        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);

*/

        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("Game");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoiceRepo.save(invoice);

        Invoice invoice2 = new Invoice();

        invoice2.setItem_id(12345);
        invoice2.setItem_type("Tshirt");
        invoice2.setName("Jane Doe");
        invoice2.setStreet("234 Mack Lane");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("23456");
        invoice2.setQuantity(2);
        invoice2.setUnit_price(BigDecimal.valueOf(20.99));
        invoice2.setSubtotal(BigDecimal.valueOf(41.98));
        invoice2.setTax(BigDecimal.valueOf(0.07));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice2.setTotal(BigDecimal.valueOf(46.99));

        invoiceRepo.save(invoice2);

        Optional<Invoice> foundInvoice =invoiceRepo.findById(invoice.getId());
        assertEquals(foundInvoice.get(), invoice);

        Optional<Invoice> foundInvoice2 =invoiceRepo.findById(invoice2.getId());
        assertEquals(foundInvoice2.get(), invoice2);

    }

    @Test
    public void updateInvoice(){

        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoiceRepo.save(invoice);

        invoice.setQuantity(5);
        invoice.setName("John Doer");

        invoiceRepo.save(invoice);
        Optional<Invoice> invoice1 = invoiceRepo.findById(invoice.getId());

        assertEquals(invoice1.get(), invoice);
    }


    @Test
    public void deleteInvoice(){

        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoiceRepo.save(invoice);


        invoiceRepo.deleteById(invoice.getId());

        Optional<Invoice> invoice1 = invoiceRepo.findById(invoice.getId());
        assertFalse(invoice1.isPresent());
    }
}
