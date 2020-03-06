package com.inventoryapi.controller;


import com.inventoryapi.entity.AdminCredentials;
import com.inventoryapi.service.AuthServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AuthServiceAdmin authServiceAdmin;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
		public ResponseEntity<Object> login(@RequestBody AdminCredentials cred) {
			try {
				return new ResponseEntity<Object>(authServiceAdmin.login(cred), HttpStatus.OK);
		}catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}

