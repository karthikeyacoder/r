
package com.klef.fsd.sdp.service;

import java.util.List;

import com.klef.fsd.sdp.model.ServiceBooking;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.HomeService;
import com.klef.fsd.sdp.model.SaveforLater;
import com.klef.fsd.sdp.model.Wishlist;

public interface CustomerService 
{
  public String customerregistration(Customer customer);
  public Customer checkcustomerlogin(String username,String password);
  
  public String customerupdateprofile(Customer customer);
  
  public List<HomeService> viewallservices();
  
  public Customer getCustomerById(int cid);
  public HomeService getServiceById(int sid);
  
  public String bookservice(ServiceBooking serviceBooking);
  public List<ServiceBooking> getbookedservicesByCustomer(int cid);

  // New methods for HomeService CRUD
  public String addHomeService(HomeService homeService);
  public String updateHomeService(HomeService homeService);
  public String deleteHomeService(int id);

  // New methods for ServiceBooking CRUD
  public String addServiceBooking(ServiceBooking serviceBooking);
  public String updateServiceBooking(ServiceBooking serviceBooking);
  public String deleteServiceBooking(int id);

  // New methods for SaveforLater CRUD
  public String addSaveforLater(SaveforLater saveforLater);
  public String deleteSaveforLater(int id);
  public List<SaveforLater> viewSaveforLaterByCustomer(int customerId);

  // New methods for Wishlist CRUD
  public String addWishlist(Wishlist wishlist);
  public String deleteWishlist(int id);
  public List<Wishlist> viewWishlistByCustomer(int customerId);
}
