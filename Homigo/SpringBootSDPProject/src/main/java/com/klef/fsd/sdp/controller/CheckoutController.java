package com.klef.fsd.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsd.sdp.model.Checkout;
import com.klef.fsd.sdp.service.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/add")
    public ResponseEntity<String> addCheckout(@RequestBody Checkout checkout) {
        String response = checkoutService.addCheckout(checkout);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCheckout(@PathVariable int id) {
        String response = checkoutService.deleteCheckout(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Checkout>> viewAllCheckouts() {
        List<Checkout> checkouts = checkoutService.viewAllCheckouts();
        return ResponseEntity.ok(checkouts);
    }
}
