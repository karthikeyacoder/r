package com.klef.fsd.sdp.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsd.sdp.model.ServiceBooking;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.HomeService;
import com.klef.fsd.sdp.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*") // * means any URL
public class CustomerController 
{
   @Autowired
   private CustomerService customerService;
	
   @GetMapping("/")
   public String home()
   {
	   return "Home Services Booking Platform";
   }
   
   @PostMapping("/registration")
   public ResponseEntity<String> customerregistration(@RequestBody Customer customer)
   {
	   try
	   {
		  String output = customerService.customerregistration(customer);
		  return ResponseEntity.ok(output); // 200 - success
	   }
	   catch(Exception e)
	   {
		   return ResponseEntity.status(500).body("Customer Registration Failed ...");
	   }
   }
   
   @PostMapping("/checkcustomerlogin")
   public ResponseEntity<?> checkcustomerlogin(@RequestBody Customer customer) 
   {
       try 
       {
           Customer c = customerService.checkcustomerlogin(customer.getUsername(), customer.getPassword());

           if (c!=null) 
           {
               return ResponseEntity.ok(c); // if login is successful
           } 
           else 
           {
               return ResponseEntity.status(401).body("Invalid Username or Password"); // if login is fail
           }
       } 
       catch (Exception e) 
       {
           return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
       }
   }
   
   @PutMapping("/updateprofile")
   public ResponseEntity<String> customerupdateprofile(@RequestBody Customer customer)
   {
 	  try
 	   {
 		  System.out.println(customer.toString());
 		  String output = customerService.customerupdateprofile(customer);
 		  return ResponseEntity.ok(output);
 	   }
 	   catch(Exception e)
 	   {
 		    System.out.println(e.getMessage());
 		    return ResponseEntity.status(500).body("Failed to Update Customer ... !!"); 
 	   }
   }

   @GetMapping("/viewallservices")
   public ResponseEntity<List<HomeService>> viewallservices()
   {
 	 List<HomeService> services =  customerService.viewallservices();
 	 
 	 return ResponseEntity.ok(services); // 200 - success
   }
   
  
   @PostMapping("/bookservice")
   public ResponseEntity<String> bookservice(@RequestBody ServiceBooking serviceBooking) 
   {
      try
      {
    	  int bookingId = new Random().nextInt(900000) + 100000;  // 6-digit ID
          serviceBooking.setId(bookingId);

          Customer customer = customerService.getCustomerById(serviceBooking.getCustomer().getId());
          HomeService service = customerService.getServiceById(serviceBooking.getService().getId());
         

          // Assign actual customer and service objects
          serviceBooking.setCustomer(customer);
          serviceBooking.setService(service);

          // Set status to "BOOKED"
          serviceBooking.setStatus("BOOKED");

          String output = customerService.bookservice(serviceBooking);

          return ResponseEntity.ok(output); // 200 - success
      }
      catch (Exception e) 
      {
    	  return ResponseEntity.status(500).body("Failed to Book a Service: " + e.getMessage());
	  }
   }

   @GetMapping("/bookedservices/{cid}")
   public ResponseEntity<List<ServiceBooking>> getServicesByCustomer(@PathVariable int cid) 
   {
       List<ServiceBooking> bookedservices =  customerService.getbookedservicesByCustomer(cid);
   	 return ResponseEntity.ok(bookedservices); // 200 - success
   }  
   
   // New endpoints for HomeService CRUD operations

   @PostMapping("/addhomeservice")
   public ResponseEntity<String> addHomeService(@RequestBody HomeService homeService) {
       try {
           String result = customerService.addHomeService(homeService);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to add Home Service: " + e.getMessage());
       }
   }

   @PutMapping("/updatehomeservice")
   public ResponseEntity<String> updateHomeService(@RequestBody HomeService homeService) {
       try {
           String result = customerService.updateHomeService(homeService);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to update Home Service: " + e.getMessage());
       }
   }

   @DeleteMapping("/deletehomeservice/{id}")
   public ResponseEntity<String> deleteHomeService(@PathVariable int id) {
       try {
           String result = customerService.deleteHomeService(id);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to delete Home Service: " + e.getMessage());
       }
   }

   // New endpoints for ServiceBooking CRUD operations

   @PostMapping("/addservicebooking")
   public ResponseEntity<String> addServiceBooking(@RequestBody ServiceBooking serviceBooking) {
       try {
           String result = customerService.addServiceBooking(serviceBooking);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to add Service Booking: " + e.getMessage());
       }
   }

   @PutMapping("/updateservicebooking")
   public ResponseEntity<String> updateServiceBooking(@RequestBody ServiceBooking serviceBooking) {
       try {
           String result = customerService.updateServiceBooking(serviceBooking);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to update Service Booking: " + e.getMessage());
       }
   }

   @DeleteMapping("/deleteservicebooking/{id}")
   public ResponseEntity<String> deleteServiceBooking(@PathVariable int id) {
       try {
           String result = customerService.deleteServiceBooking(id);
           return ResponseEntity.ok(result);
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Failed to delete Service Booking: " + e.getMessage());
       }
   }
}
