package com.klef.fsd.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.ServiceBooking;
import com.klef.fsd.sdp.model.ServiceProvider;
import com.klef.fsd.sdp.repository.ServiceBookingRepository;
import com.klef.fsd.sdp.repository.ServiceProviderRepository;
import com.klef.fsd.sdp.repository.ServiceRepository;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService
{
	@Autowired
    private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
    private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceBookingRepository serviceBookingRepository;
	
	@Override
	public ServiceProvider checkServiceProviderLogin(String username, String password) 
	{
		return serviceProviderRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addService(com.klef.fsd.sdp.model.Service service) 
	{
		serviceRepository.save(service);
		return "Service Added Successfully";
	}

	@Override
	public ServiceProvider getServiceProviderById(int serviceProviderId) 
	{
	   return serviceProviderRepository.findById(serviceProviderId).get();
	}

	@Override
	public List<com.klef.fsd.sdp.model.Service> viewServicesByProvider(int serviceProviderId) 
	{
		 ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId).orElse(null);
		 return serviceRepository.findByServiceProvider(serviceProvider);
	}

	@Override
	public List<ServiceBooking> getBookingsByProvider(int serviceProviderId) 
	{
		return serviceBookingRepository.getBookingsByProvider(serviceProviderId);
	}

	@Override
	public String updateBookingStatus(int id, String status) 
	{
		serviceBookingRepository.updateStatusById(status,id);
		return "Booking Status Updated Successfully";
	}

}
