package com.company.gamestore.service;

import com.company.gamestore.models.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private TShirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;
    private FeeRepository feeRepository;

    private TaxRepository taxRepository;


    @Before
    public void setUp() throws Exception {
        setUpinvoiceRepositoryMock();
        service = new ServiceLayer(consoleRepository, gameRepository, tshirtRepository,
                invoiceRepository, taxRepository, feeRepository);
    }


    private void setUpinvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice = new Invoice();

        invoice.setItem_id(1);
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

        Invoice invoice2 = new Invoice();

        invoice2.setItem_id(2);
        invoice2.setName("John Doe");
        invoice2.setStreet("234 Mack Lane");
        invoice2.setItem_type("TShirt");
        invoice2.setCity("Atlanta");
        invoice2.setName("John Doe");
        invoice2.setState("NJ");
        invoice2.setZipcode("07105");
        invoice2.setQuantity(2);
        invoice2.setUnit_price(BigDecimal.valueOf(20.99));
        invoice2.setSubtotal(BigDecimal.valueOf(41.98));
        invoice2.setTax(BigDecimal.valueOf(0.07));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice2.setTotal(BigDecimal.valueOf(46.90));

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        doReturn(invoice).when(invoiceRepository).save(invoice);
        doReturn(invoice2).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(Optional.of(invoice2)).when(invoiceRepository).findById(2);
        doReturn(iList).when(invoiceRepository).findAll();
    }

    @Test
    public void shouldFindInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel();

        ivm.setItem_id(1);
        ivm.setItem_type("TShirt");
        ivm.setName("John Doe");
        ivm.setStreet("200 Ferry Street");
        ivm.setCity("Newark");
        ivm.setState("NJ");
        ivm.setZipcode("07105");
        ivm.setQuantity(1);
        ivm.setUnit_price(BigDecimal.valueOf(15.99));
        ivm.setSubtotal(BigDecimal.valueOf(15.99));
        ivm.setTax(BigDecimal.valueOf(0.05));
        ivm.setProcessing_fee(BigDecimal.valueOf(1.98));
        ivm.setTotal(BigDecimal.valueOf(18.77));

        InvoiceViewModel ivmToFind = service.findInvoice(1);
        assertEquals(ivm, ivmToFind);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel ivm = new InvoiceViewModel();

        ivm.setItem_id(1);
        ivm.setItem_type("TShirt");
        ivm.setName("John Doe");
        ivm.setStreet("200 Ferry Street");
        ivm.setCity("Newark");
        ivm.setState("NJ");
        ivm.setZipcode("07105");
        ivm.setQuantity(1);
        ivm.setUnit_price(BigDecimal.valueOf(15.99));
        ivm.setSubtotal(BigDecimal.valueOf(15.99));
        ivm.setTax(BigDecimal.valueOf(0.05));
        ivm.setProcessing_fee(BigDecimal.valueOf(1.98));
        ivm.setTotal(BigDecimal.valueOf(18.77));

        InvoiceViewModel ivm2 = new InvoiceViewModel();

        ivm2.setItem_id(2);
        ivm2.setItem_type("Consoles");
        ivm2.setName("John Doe");
        ivm2.setStreet("200 Ferry Street");
        ivm2.setCity("Newark");
        ivm2.setState("NJ");
        ivm2.setZipcode("07105");
        ivm2.setQuantity(1);
        ivm2.setUnit_price(BigDecimal.valueOf(15.99));
        ivm2.setSubtotal(BigDecimal.valueOf(15.99));
        ivm2.setTax(BigDecimal.valueOf(0.05));
        ivm2.setProcessing_fee(BigDecimal.valueOf(1.98));
        ivm2.setTotal(BigDecimal.valueOf(18.77));

        List<InvoiceViewModel> ivmList = service.findAllInvoices();

        assertEquals(2, ivmList.size());
    }

    @Test
    public void shouldFindInvoicebyName() {
        InvoiceViewModel ivm = new InvoiceViewModel();

        ivm.setItem_id(1);
        ivm.setItem_type("TShirt");
        ivm.setName("Melissa");
        ivm.setStreet("200 Ferry Street");
        ivm.setCity("Newark");
        ivm.setState("NJ");
        ivm.setZipcode("07105");
        ivm.setQuantity(1);
        ivm.setUnit_price(BigDecimal.valueOf(15.99));
        ivm.setSubtotal(BigDecimal.valueOf(15.99));
        ivm.setTax(BigDecimal.valueOf(0.05));
        ivm.setProcessing_fee(BigDecimal.valueOf(1.98));
        ivm.setTotal(BigDecimal.valueOf(18.77));

        List<InvoiceViewModel> ivmList = new ArrayList<>();
        ivmList.add(ivm);
        List<InvoiceViewModel> foundivmList = service.findInvoicebyName("Melissa");

        assertEquals(ivmList, foundivmList);
    }

    @Test
    public void shouldSaveInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel();

        ivm.setItem_id(1);
        ivm.setItem_type("TShirt");
        ivm.setName("John Doe");
        ivm.setStreet("200 Ferry Street");
        ivm.setCity("Newark");
        ivm.setState("NJ");
        ivm.setZipcode("07105");
        ivm.setQuantity(1);
        ivm.setUnit_price(BigDecimal.valueOf(15.99));
        ivm.setSubtotal(BigDecimal.valueOf(15.99));
        ivm.setTax(BigDecimal.valueOf(0.05));
        ivm.setProcessing_fee(BigDecimal.valueOf(1.98));
        ivm.setTotal(BigDecimal.valueOf(18.77));

        InvoiceViewModel expectedIvm = new InvoiceViewModel();

        expectedIvm.setItem_id(1);
        expectedIvm.setItem_type("TShirt");
        expectedIvm.setName("John Doe");
        expectedIvm.setStreet("200 Ferry Street");
        expectedIvm.setCity("Newark");
        expectedIvm.setState("NJ");
        expectedIvm.setZipcode("07105");
        expectedIvm.setQuantity(1);
        expectedIvm.setUnit_price(BigDecimal.valueOf(15.99));
        expectedIvm.setSubtotal(BigDecimal.valueOf(15.99));
        expectedIvm.setTax(BigDecimal.valueOf(0.05));
        expectedIvm.setProcessing_fee(BigDecimal.valueOf(1.98));
        expectedIvm.setTotal(BigDecimal.valueOf(18.77));

        InvoiceViewModel createdIvm = service.saveInvoice(ivm);

        assertEquals(expectedIvm, createdIvm);
    }
}
