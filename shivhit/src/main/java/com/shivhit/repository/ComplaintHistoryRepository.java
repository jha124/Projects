package com.shivhit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivhit.model.ComplaintHistory;

public interface ComplaintHistoryRepository extends JpaRepository<ComplaintHistory, Long> {
    // Additional query methods if needed
}
