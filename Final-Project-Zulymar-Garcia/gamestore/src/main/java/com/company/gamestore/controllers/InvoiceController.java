package com.company.gamestore.controllers;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/invoices/{name}")
    public List<InvoiceViewModel> getInvoiceByName(@PathVariable String name) {
        return serviceLayer.findInvoicebyName(name);
    }

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

}
