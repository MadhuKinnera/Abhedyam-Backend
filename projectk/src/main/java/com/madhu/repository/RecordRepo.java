package com.madhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.entity.SaleRecord;

public interface RecordRepo extends JpaRepository<SaleRecord, Integer> {

}
