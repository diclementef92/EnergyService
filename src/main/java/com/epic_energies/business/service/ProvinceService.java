package com.epic_energies.business.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Province;
import com.epic_energies.business.repository.ProvinceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProvinceService {

	@Autowired ProvinceRepository provRepo;
	
	// internal methods
	
	public void importProvinces() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src\\main\\resources\\data\\Province.csv"));
		sc.useDelimiter(",");
		
		while (sc.hasNext()) {

			String[] fileToString = sc.next().split("\n");
			for (int i = 1; i < fileToString.length; i++) {
				String[] pToString = fileToString[i].split(";");
				Province p = Province.builder().abbr(pToString[0]).name(pToString[1]).county(pToString[2]).build();
				persistProvince(p);
			}
		}
		
		sc.close();
	}
	
	// jpa methods
	
	public void persistProvince(Province p) {
		provRepo.save(p);
		log.info("province correctly persisted on Database");
	}
	
	public List<Province> findAllProvinces() {
		return (List<Province>) provRepo.findAll();
	}
}
