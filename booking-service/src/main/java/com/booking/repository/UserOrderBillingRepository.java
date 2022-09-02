package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.UserOrderBillingEntity;

public interface UserOrderBillingRepository extends JpaRepository<UserOrderBillingEntity, Long> {

}
