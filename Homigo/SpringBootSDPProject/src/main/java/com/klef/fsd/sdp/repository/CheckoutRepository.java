package com.klef.fsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    // Additional query methods if needed
}
