package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.UserOrderEntity;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

}
