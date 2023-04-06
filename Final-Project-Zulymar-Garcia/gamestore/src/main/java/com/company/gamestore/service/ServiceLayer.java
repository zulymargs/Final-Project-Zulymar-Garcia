package com.company.gamestore.service;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.repository.*;
import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.Tax;
import com.company.gamestore.models.TShirt;
import com.company.gamestore.models.Fee;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ServiceLayer {

    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TShirtRepository tshirtRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private TaxRepository taxRepository;


    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository, GameRepository gameRepository,
                        TShirtRepository tshirtRepository, InvoiceRepository invoiceRepository,
                        TaxRepository taxRepository, FeeRepository feeRepository) {
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
        this.feeRepository = feeRepository;
        this.taxRepository = taxRepository;
    }

    public InvoiceViewModel findInvoice(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return buildInvoiceViewModel(invoice.get());
        } else {
            throw new NotFoundException("Invoice with that ID not found.");
        }
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        if (ivmList != null) {
            return ivmList;
        } else {
            throw new NotFoundException("Invoices with that Name not found.");
        }
    }

    public List<InvoiceViewModel> findInvoicebyName(String name) {
        List<Invoice> invoiceList = invoiceRepository.findByName(name);

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        if (ivmList != null) {
            return ivmList;
        } else {
            throw new NotFoundException("Invoices with that Name not found.");
        }
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice i = new Invoice();

        i.setItem_type(invoiceViewModel.getItem_type());
        i.setCity(invoiceViewModel.getCity());
        i.setState(invoiceViewModel.getState());
        i.setName(invoiceViewModel.getName());
        i.setTax(invoiceViewModel.getTax());
        i.setQuantity(invoiceViewModel.getQuantity());
        i.setSubtotal(invoiceViewModel.getSubtotal());
        i.setTotal(invoiceViewModel.getTotal());
        i.setProcessing_fee(invoiceViewModel.getProcessing_fee());
        i.setUnit_price(invoiceViewModel.getUnit_price());
        i.setZipcode(invoiceViewModel.getZipcode());
        i.setStreet(invoiceViewModel.getStreet());

        i = invoiceRepository.save(i);
        invoiceViewModel.setId(i.getId());

        return invoiceViewModel;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getId());
        ivm.setName(invoice.getName());
        ivm.setCity(invoice.getCity());
        ivm.setStreet(invoice.getStreet());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItem_type(invoice.getItem_type());
        ivm.setItem_id(invoice.getItem_id());
        ivm.setQuantity(invoice.getQuantity());

        Tax tax = taxRepository.findByState(ivm.getState());
        ivm.setTax(tax.getRate());

        Fee fee = feeRepository.findByProductType(ivm.getItem_type());
        if(ivm.getQuantity() > 10) {
            BigDecimal d = BigDecimal.valueOf(15.49);
            fee.setFee(fee.getFee().add(d));
        }
        ivm.setProcessing_fee(fee.getFee());
        BigDecimal price = BigDecimal.valueOf(0);

        if(ivm.getItem_type() == "Game") {
            Optional<Game> game = gameRepository.findById(ivm.getItem_id());
            Game g = game.get();
            price = g.getPrice();

        }
        else if(ivm.getItem_type() == "Console") {
            Optional<Console> console = consoleRepository.findById(ivm.getItem_id());
            Console c = console.get();
            price = c.getPrice();
        }
        else if (ivm.getItem_type() == "TShirt") {
            Optional<TShirt> tshirt = tshirtRepository.findById(ivm.getItem_id());
            TShirt t = tshirt.get();
            price = t.getPrice();
        }

        BigDecimal subtotal = price.multiply(BigDecimal.valueOf(ivm.getQuantity()));
        ivm.setSubtotal(subtotal);
        BigDecimal total = tax.getRate().multiply(subtotal).add(subtotal).add(fee.getFee());
        ivm.setTotal(total);

        return ivm;

    }


}
