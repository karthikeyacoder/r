

package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.ServiceBooking;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.HomeService;
import com.klef.fsd.sdp.model.SaveforLater;
import com.klef.fsd.sdp.model.Wishlist;
import com.klef.fsd.sdp.repository.ServiceBookingRepository;
import com.klef.fsd.sdp.repository.CustomerRepository;
import com.klef.fsd.sdp.repository.HomeServiceRepository;
import com.klef.fsd.sdp.repository.SaveforLaterRepository;
import com.klef.fsd.sdp.repository.WishlistRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private HomeServiceRepository homeServiceRepository;
	
	@Autowired
	private ServiceBookingRepository serviceBookingRepository;

	@Autowired
	private SaveforLaterRepository saveforLaterRepository;

	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Override
	public String customerregistration(Customer customer) 
	{
		customerRepository.save(customer);
		return "Customer Registered Successfully";
	}

	@Override
	public Customer checkcustomerlogin(String username, String password) 
	{
		return customerRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String customerupdateprofile(Customer customer) 
	{
		Optional<Customer> object	= customerRepository.findById(customer.getId());
		
		String msg = null;
			  
			  if(object.isPresent())
			  {
				  Customer c = object.get();
				  
				  c.setName(customer.getName());
				  c.setDob(customer.getDob());
				  c.setMobileno(customer.getMobileno());
				  c.setEmail(customer.getEmail());
				  c.setPassword(customer.getPassword());
				  c.setLocation(customer.getLocation());
				  
				  customerRepository.save(customer);
				  
				  msg = "Customer Profile Updated Successfully";
			  }
			  else
			  {
				  msg = "Customer ID Not Found to Update";
			  }
			  return msg;
	}

	@Override
	public List<HomeService> viewallservices() 
	{
	   return homeServiceRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int cid) 
	{
		return customerRepository.findById(cid).orElse(null);
	}

	@Override
	public HomeService getServiceById(int sid) 
	{
		return homeServiceRepository.findById(sid).orElse(null);
	}

	@Override
	public String bookservice(ServiceBooking serviceBooking) 
	{
		serviceBookingRepository.save(serviceBooking);
		return "Service Booked Successfully";
	}

	@Override
	public List<ServiceBooking> getbookedservicesByCustomer(int cid) 
	{
		Customer customer = customerRepository.findById(cid).orElse(null);
		return serviceBookingRepository.findByCustomer(customer);
	}

	@Override
	public String addHomeService(HomeService homeService) {
		homeServiceRepository.save(homeService);
		return "Home Service Added Successfully";
	}

	@Override
	public String updateHomeService(HomeService homeService) {
		Optional<HomeService> existingService = homeServiceRepository.findById(homeService.getId());
		if(existingService.isPresent()) {
			homeServiceRepository.save(homeService);
			return "Home Service Updated Successfully";
		} else {
			return "Home Service Not Found";
		}
	}

	@Override
	public String deleteHomeService(int id) {
		Optional<HomeService> existingService = homeServiceRepository.findById(id);
		if(existingService.isPresent()) {
			homeServiceRepository.deleteById(id);
			return "Home Service Deleted Successfully";
		} else {
			return "Home Service Not Found";
		}
	}

	@Override
	public String addServiceBooking(ServiceBooking serviceBooking) {
		serviceBookingRepository.save(serviceBooking);
		return "Service Booking Added Successfully";
	}

	@Override
	public String updateServiceBooking(ServiceBooking serviceBooking) {
		Optional<ServiceBooking> existingBooking = serviceBookingRepository.findById(serviceBooking.getId());
		if(existingBooking.isPresent()) {
			serviceBookingRepository.save(serviceBooking);
			return "Service Booking Updated Successfully";
		} else {
			return "Service Booking Not Found";
		}
	}

	@Override
	public String deleteServiceBooking(int id) {
		Optional<ServiceBooking> existingBooking = serviceBookingRepository.findById(id);
		if(existingBooking.isPresent()) {
			serviceBookingRepository.deleteById(id);
			return "Service Booking Deleted Successfully";
		} else {
			return "Service Booking Not Found";
		}
	}

	@Override
	public String addSaveforLater(SaveforLater saveforLater) {
		saveforLaterRepository.save(saveforLater);
		return "Service saved for later successfully";
	}

	@Override
	public String deleteSaveforLater(int id) {
		Optional<SaveforLater> existing = saveforLaterRepository.findById(id);
		if(existing.isPresent()) {
			saveforLaterRepository.deleteById(id);
			return "Saved service deleted successfully";
		} else {
			return "Saved service not found";
		}
	}

	@Override
	public List<SaveforLater> viewSaveforLaterByCustomer(int customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		return saveforLaterRepository.findByCustomer(customer);
	}

	@Override
	public String addWishlist(Wishlist wishlist) {
		wishlistRepository.save(wishlist);
		return "Service added to wishlist successfully";
	}

	@Override
	public String deleteWishlist(int id) {
		Optional<Wishlist> existing = wishlistRepository.findById(id);
		if(existing.isPresent()) {
			wishlistRepository.deleteById(id);
			return "Wishlist entry deleted successfully";
		} else {
			return "Wishlist entry not found";
		}
	}

	@Override
	public List<Wishlist> viewWishlistByCustomer(int customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		return wishlistRepository.findByCustomer(customer);
	}
}
