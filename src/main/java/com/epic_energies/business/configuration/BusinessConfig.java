package com.epic_energies.business.configuration;

import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.E_AddressType;
import com.epic_energies.business.model.E_CustomerType;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.repository.AdressDAO;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {

    @Autowired AdressDAO opertiveAddressDao;
    private Faker fake = Faker.instance(new Locale("en-EN"));
    
    @Bean("FakeLegAddress")
    @Scope("prototype")
    public Address fakeLegAddress() {
    	    	
    	return Address.builder()
    			.streetName(fake.address().streetAddress())
    			.streetNumber(fake.number().numberBetween(1, 100))
    			.place(fake.address().cityName())
    			.postCode(fake.number().numberBetween(10000, 100000))
    			.addressType(E_AddressType.LEGAL_ADDRESS)
    			.build();
    }
    
    @Bean("FakeOpAddress")
    @Scope("prototype")
    public Address fakeOpAddress() {
     	
    	return Address.builder()
    			.streetName(fake.address().streetAddress())
    			.streetNumber(fake.number().numberBetween(1, 100))
    			.place(fake.address().cityName())
    			.postCode(fake.number().numberBetween(10000, 100000))
    			.addressType(E_AddressType.OPERATIVE_ADDRESS)
    			.build();
    }

    @Bean("FakeCustomer")
    @Scope("prototype")
    public Customer fakeCustomer() {
    	
	String companyName = fake.company().name();
	String[] companyArray = companyName.split(" ");
	String companyEmail = "info_" + "@" + companyArray[0] + ".com";
	String fName = fake.name().firstName();
	String lName = fake.name().lastName();
	String contactEmail = fName.substring(0, 1) + "." + lName + "@example.com";
	int randomType = fake.number().numberBetween(0, 3);
	E_CustomerType companyType = getRandomCustomeType(randomType);
	Integer annualIncome = getRandomIncome(companyType);
	String pec;
	if (companyArray.length > 1) {
		pec = companyArray[0] + "." + companyArray[1] + "@pec.it";		
	} else {
		pec = companyArray[0] + "@pec.it";
	}
	
	
	return Customer.builder()
			.businessName(companyName)
			.email(companyEmail)
			.pec(pec)
			.vatNumber(fake.number().numberBetween(10000000001l, 100000000000l))
			.phoneNumber(3490000000l + fake.number().numberBetween(1l, 10000000l))
			.insertData(LocalDate.of(2023, 5, (int) Math.floor(Math.random() * 30 + 1)))
			.lastContactData(LocalDate.of(2023, 5, (int) Math.floor(Math.random() * 30 + 1)))
			.contactName(fName + " " + lName)
			.contactEmail(contactEmail)
			.contactPhone(3490000000l + fake.number().numberBetween(1l, 10000000l))
			.costumerType(companyType)
			.annualIncome(annualIncome)
			.build();
    }
    
    private E_CustomerType getRandomCustomeType(int random) {
    	
    	E_CustomerType type = null;
    	switch (random) {
    	case 0 -> type = E_CustomerType.PA;
    	case 1 -> type = E_CustomerType.SAS;
    	case 2 -> type = E_CustomerType.SPA;
    	case 3 -> type = E_CustomerType.SRL;
    	}
    	return type;
    }
    
    public Integer getRandomIncome(E_CustomerType cusType) {
    	
    	Integer income = null;
    	switch (cusType) {
    	case PA ->  income = fake.number().numberBetween(50000, 250000);
    	case SAS -> income = fake.number().numberBetween(250000, 1000000);
    	case SPA -> income = fake.number().numberBetween(1000000, 50000000);
    	case SRL -> income = fake.number().numberBetween(1000000, 50000000);
    	}
    	return income;
    }
}
