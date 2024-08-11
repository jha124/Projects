package com.shivhit.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivhit.exception.DataNotFoundException;
import com.shivhit.model.Complaint;
import com.shivhit.model.ComplaintHistory;
import com.shivhit.model.Status;
import com.shivhit.repository.ComplaintRepository;

import jakarta.transaction.Transactional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	private static final Logger logger = LoggerFactory.getLogger(ComplaintServiceImpl.class);

	@Autowired
	private ComplaintRepository complaintRepo;

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintRepo.findAll();
	}

	@Override
	@Transactional
	public Complaint addComplaint(Complaint complaint) {
		LocalDateTime now = LocalDateTime.now();
		complaint.setDate(now.toLocalDate());
		complaint.setTime(now.toLocalTime());
		complaint.setTicketno(UUID.randomUUID().toString());
		complaint.setStatus(Status.New);

		// Save the complaint
		complaint = complaintRepo.save(complaint);

		// Save history for adding complaint
		saveHistory(complaint, Status.New, Status.New);

		return complaint;
	}

	@Override
	public Complaint getById(Integer id) {
		return complaintRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Complaint not found"));
	}

	@Override
	public Complaint getByTicketNo(String ticketno) {
		return complaintRepo.findByTicketno(ticketno)
				.orElseThrow(() -> new DataNotFoundException("Complaint not found"));
	}

	@Override
	public Complaint updateStatus(Integer id, Status newStatus) {
		Complaint complaint = complaintRepo.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Complaint not found"));

		Status oldStatus = complaint.getStatus();
		complaint.setStatus(newStatus);

		// Save the updated complaint
		complaint = complaintRepo.save(complaint);

		// Save history for status update
		saveHistory(complaint, oldStatus, newStatus);

		return complaint;
	}

	@Override
	public Complaint closeComplaint(Integer id) {
		Complaint complaint = complaintRepo.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Complaint not found"));
		Status oldStatus = complaint.getStatus();

		complaint.setStatus(Status.Closed);
		logger.info("Closing complaint id: " + id);

		// Save the closed complaint
		complaint = complaintRepo.save(complaint);

		// Save history for closing the complaint
		saveHistory(complaint, oldStatus, Status.Closed);

		return complaint;
	}

	private void saveHistory(Complaint complaint, Status oldStatus, Status newStatus) {
		if (complaint.getHistory() == null) {
			complaint.setHistory(new ArrayList<>());
		}

		ComplaintHistory history = new ComplaintHistory();
		history.setComplaint(complaint);
		history.setChangeDateTime(LocalDateTime.now());
		history.setOldStatus(oldStatus != null ? oldStatus : Status.New); // Default to Status.New if oldStatus is null
		history.setNewStatus(newStatus);

		// Add history to complaint's history list
		complaint.getHistory().add(history);
		logger.info("History entry added: " + history);
	}
}
