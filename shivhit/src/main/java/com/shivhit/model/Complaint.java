package com.shivhit.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	@Column(nullable = false, unique = true)
	private String ticketno;
	@Column(nullable = false)
	private LocalDate date;
	@Column(nullable = false)
	private LocalTime time;
	@Column(nullable = false)
	private String projectname;
	@Column(nullable = false)
	private String modulename;
	@Column(nullable = false)
	private String subModulename;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Frequency frequency;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@Column(nullable = false)
	private String explainyourissue;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ComplaintHistory> history = new ArrayList<>();

    @Override
    public String toString() {
        return "Complaint{" +
                "cid=" + cid +
                ", ticketno='" + ticketno + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", projectname='" + projectname + '\'' +
                ", modulename='" + modulename + '\'' +
                ", subModulename='" + subModulename + '\'' +
                ", frequency=" + frequency +
                ", priority=" + priority +
                ", explainyourissue='" + explainyourissue + '\'' +
                ", status=" + status +
                ", historyIds=" + getHistoryIds() +
                '}';
    }

    private List<Long> getHistoryIds() {
        List<Long> ids = new ArrayList<>();
        for (ComplaintHistory ch : history) {
            ids.add(ch.getId());
        }
        return ids;
    }

}
