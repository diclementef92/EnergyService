package com.epic_energies.business.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@GetMapping("/{id}")
	public ResponseEntity<?> findInvoiceById(@PathVariable Long id) {
	return new ResponseEntity<>( HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllInvoices() {
	return new ResponseEntity<>( HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewInvoice(@RequestBody String a) {
		return new ResponseEntity<>( HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateInvoice(@RequestBody String a) {
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteInvoice(@RequestBody String a) {
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}