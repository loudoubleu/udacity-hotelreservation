package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService = new CustomerService();
    private Map<String, Customer> customers = new HashMap<String, Customer>();

    private CustomerService() {}

    public static CustomerService getInstance() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        try {
            return customers.get(customerEmail);
        }
        catch (NullPointerException ex) {
            System.out.println(customerEmail + " is not a registered email.");
        }

        return null;
    }

    public HashMap<String, Customer> getAllCustomers() {
        if (customers.size() == 0) {
            System.out.println("There are currently no customers");
        }

        return (HashMap<String, Customer>) customers;
    }
}
