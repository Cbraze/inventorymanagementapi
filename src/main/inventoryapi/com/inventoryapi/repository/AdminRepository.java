package com.inventoryapi.repository;


import com.inventoryapi.entity.Admin;
import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	public Admin findByAdminUsername(String username);

}