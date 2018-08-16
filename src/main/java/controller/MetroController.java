package controller;

import model.CsvFileInfo;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.CustomerDAO;
import service.CsvFileConverterService;

import java.io.IOException;

@RestController
public class MetroController {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CsvFileConverterService csvFileConverterService;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<CsvFileInfo> saveCustomer(@RequestBody Customer customer) throws IOException {
        System.out.println("Send post request to localhost:8080/customer :\n" + customer.toString());
        customerDAO.save(customer);
        CsvFileInfo csvFileInfo = csvFileConverterService.convertToFile(customer);
        System.out.println("Getting response :\n" + csvFileInfo.toString());

        return new ResponseEntity<CsvFileInfo>(csvFileInfo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/{uniqueFileName}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerByFileName(@PathVariable String uniqueFileName) throws IOException {
        System.out.println("Send get request to localhost:8080/customer/" + uniqueFileName);
        Customer customer = csvFileConverterService.convertToCustomer(uniqueFileName);
        System.out.println("Getting response :\n" + customer.toString());

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
}
