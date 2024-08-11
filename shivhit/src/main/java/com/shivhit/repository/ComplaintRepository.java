package com.shivhit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivhit.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	public Optional<Complaint> findByTicketno(String ticketno);
}
