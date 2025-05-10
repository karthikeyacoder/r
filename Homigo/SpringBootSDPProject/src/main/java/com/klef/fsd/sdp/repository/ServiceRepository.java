package com.klef.fsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.Service;
import com.klef.fsd.sdp.model.ServiceProvider;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> 
{
	public List<Service> findByServiceProvider(ServiceProvider serviceProvider);
	
	// Add other query methods if needed
}
