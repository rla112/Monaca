package com.dw.Monaca.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dw.Monaca.model.AdminNotice;

public interface AdminNoticeRepository extends JpaRepository<AdminNotice, Long>{

	List<AdminNotice> findAllByCreateAtBeforeAndNewStatusIsTrue(LocalDateTime threshold);


}
