package com.klef.fsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.ServiceBooking;
import com.klef.fsd.sdp.model.Customer;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Integer> 
{
    @Query("SELECT sb FROM ServiceBooking sb WHERE sb.service.serviceProvider.id = ?1")
    public List<ServiceBooking> getBookingsByProvider(int serviceProviderId);
    
    @Query("UPDATE ServiceBooking sb SET sb.status = ?1 WHERE sb.id = ?2")
    public int updateStatusById(String status, int id);

    // Added method to find bookings by customer
    List<ServiceBooking> findByCustomer(Customer customer);
}
