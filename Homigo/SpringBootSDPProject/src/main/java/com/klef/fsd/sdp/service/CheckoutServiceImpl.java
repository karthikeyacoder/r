package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Checkout;
import com.klef.fsd.sdp.repository.CheckoutRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public String addCheckout(Checkout checkout) {
        checkoutRepository.save(checkout);
        return "Checkout added successfully";
    }

    @Override
    public String deleteCheckout(int id) {
        Optional<Checkout> existing = checkoutRepository.findById(id);
        if (existing.isPresent()) {
            checkoutRepository.deleteById(id);
            return "Checkout deleted successfully";
        } else {
            return "Checkout not found";
        }
    }

    @Override
    public List<Checkout> viewAllCheckouts() {
        return checkoutRepository.findAll();
    }
}
