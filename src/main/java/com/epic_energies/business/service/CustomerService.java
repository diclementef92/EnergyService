package com.epic_energies.business.service;

import java.util.Locale;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.AddressDAO;
import com.github.javafaker.Faker;

@Service
public class CustomerService {
    @Autowired private CustomerDAO customerRep;
    @Autowired private AddressDAO AddressRep;

    @Autowired @Qualifier("FakeCustomer")
    private ObjectProvider<Customer> customerProvider;

    @Autowired @Qualifier("FakeOpAddress")
    private ObjectProvider<Address> opAddressProvider;

    @Autowired @Qualifier("FakeLegAddress")
    private ObjectProvider<Address> legalAddressProvider;

    private Faker fake = Faker.instance(new Locale("it-IT"));
    
    public void persistFakeCustomer() {
    	Customer c = customerProvider.getObject();
    	Address lAddress = legalAddressProvider.getObject();
    	AddressRep.save(lAddress);
    	c.setLegalAddress(lAddress);
    	if (fake.number().numberBetween(0, 10) > 7) {
    		Address opAddress = opAddressProvider.getObject();
    		AddressRep.save(opAddress);
    		c.setOperativeAddress(opAddress);
    	}
    	customerRep.save(c);
    }
    
    public void insertCustomer(Customer c) {
    		customerRep.save(c);
    }
    
    public String updateCustomer(Customer c) {
    	if (customerRep.existsById(c.getId())) {
    		
    	}
    	return "";
    }

}