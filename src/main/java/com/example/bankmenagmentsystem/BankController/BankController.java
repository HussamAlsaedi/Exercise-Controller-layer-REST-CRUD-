package com.example.bankmenagmentsystem.BankController;

import com.example.bankmenagmentsystem.ApiResponse.ApiResponse;
import com.example.bankmenagmentsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.RemoteRef;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/addCustomers")
    public ApiResponse addCustomers(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("Successfully added customer.");
    }

    @PutMapping("/updateCustomer/{id}")
    public ApiResponse UpdateCustomer(@PathVariable int id, @RequestBody Customer customer) {

        Customer existingCus = null;
        for (Customer c1 : customers) {
            if (c1.getID() == id) {
                existingCus = c1;
                break;
            }
        }

        if (existingCus != null) {

            existingCus.setBalance(customer.getBalance());
            return new ApiResponse("Successfully updated customer with ID " + id);
        } else {
            return new ApiResponse("Customer not found.");
        }
    }

    @DeleteMapping("/DeleteCustomer/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {

        Customer Delete = null;
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                Delete = customer;
                break;
            }
        }

        if (Delete != null) {
            customers.remove(Delete);
            return new ApiResponse("Successfully deleted customer with ID " + id);
        } else {
            return new ApiResponse("Customer not found.");
        }
    }


    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse deposit(@PathVariable int id, @PathVariable double amount) {
        Customer customer = null;
        for (Customer c1 : customers) {
            if (c1.getID() == id ) {
                customer = c1;
                break;
            }
        }

        if (customer != null) {
            if (amount > 0) {
                customer.setBalance(customer.getBalance() + amount);
                return new ApiResponse("Successfully deposited " + amount );
            } else {
                return new ApiResponse("Deposit amount must be bigger then zero.");
            }
        }
        return new ApiResponse("Customer not found.");
    }


    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdraw(@PathVariable int id, @PathVariable double amount) {
        Customer customer = null;
        for (Customer c2 : customers) {
            if (c2.getID() == id) {
                customer = c2;
                break;
            }
        }

        if (customer != null) {
            if (amount > 0) {
                if (customer.getBalance() >= amount) {
                    customer.setBalance(customer.getBalance() - amount);
                    return new ApiResponse("Successfully withdrew " + amount );
                } else {
                    return new ApiResponse("Customer has not balance.");
                }
            } else {
                return new ApiResponse("Withdrawal amount must be bigger than zero.");
            }
        }
        return new ApiResponse("Customer not found.");
    }




}
