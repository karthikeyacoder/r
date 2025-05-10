package com.klef.fsd.sdp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.ServiceProvider;
import com.klef.fsd.sdp.repository.AdminRepository;
import com.klef.fsd.sdp.repository.CustomerRepository;
import com.klef.fsd.sdp.repository.ServiceProviderRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        if (customer.isPresent()) {
            return new CustomUserDetails(customer.get());
        }

        Optional<Admin> admin = Optional.ofNullable(adminRepository.findByUsername(username));
        if (admin.isPresent()) {
            return new CustomUserDetails(admin.get());
        }

        Optional<ServiceProvider> serviceProvider = Optional.ofNullable(serviceProviderRepository.findByUsername(username));
        if (serviceProvider.isPresent()) {
            return new CustomUserDetails(serviceProvider.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
