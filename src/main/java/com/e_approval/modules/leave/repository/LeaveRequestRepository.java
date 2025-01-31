package com.e_approval.modules.leave.repository;

import com.e_approval.modules.leave.model.entity.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Long> {
}
