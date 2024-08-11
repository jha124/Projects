package com.shivhit.service;

import java.util.List;

import com.shivhit.model.Complaint;
import com.shivhit.model.Status;

public interface ComplaintService {
	public List<Complaint> getAllComplaints();

	public Complaint addComplaint(Complaint complaint);

	public Complaint getById(Integer id);

	public Complaint getByTicketNo(String ticketNo);

	public Complaint updateStatus(Integer id, Status newStatus);

	public Complaint closeComplaint(Integer id);

}
