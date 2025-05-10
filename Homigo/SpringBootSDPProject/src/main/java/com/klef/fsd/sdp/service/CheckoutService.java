package com.klef.fsd.sdp.service;

import java.util.List;

import com.klef.fsd.sdp.model.Checkout;

public interface CheckoutService {
    String addCheckout(Checkout checkout);
    String deleteCheckout(int id);
    List<Checkout> viewAllCheckouts();
}
