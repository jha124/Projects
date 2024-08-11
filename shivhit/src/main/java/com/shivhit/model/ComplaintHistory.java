package com.shivhit.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "complaint_history")
public class ComplaintHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cid", nullable = false)
	@JsonBackReference
	private Complaint complaint;

	@Column(nullable = false)
	private LocalDateTime changeDateTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status oldStatus;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status newStatus;
	
    @Override
    public String toString() {
        return "ComplaintHistory{" +
                "id=" + id +
                ", complaintId=" + (complaint != null ? complaint.getCid() : null) +
                ", changeDateTime=" + changeDateTime +
                ", oldStatus=" + oldStatus +
                ", newStatus=" + newStatus +
                '}';
    }

}
