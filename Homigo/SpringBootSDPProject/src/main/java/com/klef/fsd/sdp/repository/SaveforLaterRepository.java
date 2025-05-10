package com.klef.fsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.SaveforLater;
import com.klef.fsd.sdp.model.Customer;

@Repository
public interface SaveforLaterRepository extends JpaRepository<SaveforLater, Integer> {
    List<SaveforLater> findByCustomer(Customer customer);
}
