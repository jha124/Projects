package com.shivhit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivhit.model.Complaint;
import com.shivhit.model.Status;
import com.shivhit.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintService service;

	@GetMapping
	public ResponseEntity<List<Complaint>> listAllComplaints() {
		return ResponseEntity.ok(service.getAllComplaints());
	}

	@PostMapping
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
		return new ResponseEntity<>(service.addComplaint(complaint), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Complaint> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping("/ticketno/{ticketno}")
	public ResponseEntity<Complaint> getByTicketNo(@PathVariable String ticketno) {
		return ResponseEntity.ok(service.getByTicketNo(ticketno));
	}

	@PutMapping("/{id}/close")
	public ResponseEntity<Complaint> closeComplaint(@PathVariable Integer id) {
		return ResponseEntity.ok(service.closeComplaint(id));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Complaint> updateStatus(@PathVariable Integer id, @RequestParam Status status) {
		return ResponseEntity.ok(service.updateStatus(id, status));
	}

}
