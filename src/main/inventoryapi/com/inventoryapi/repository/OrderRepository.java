package com.inventoryapi.repository;

import com.inventoryapi.entity.Orders;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {
}
