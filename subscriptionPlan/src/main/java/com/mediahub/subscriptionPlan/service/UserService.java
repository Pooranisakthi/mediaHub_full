package com.mediahub.subscriptionPlan.service;

import com.mediahub.subscriptionPlan.dto.CreateUserRequest;
import com.mediahub.subscriptionPlan.dto.UpdateUserRequest;
import com.mediahub.subscriptionPlan.model.SubscriptionUser;
import com.mediahub.subscriptionPlan.repository.SubscriptionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("subscriptionUserService")
public class UserService {

    @Autowired
    private SubscriptionUserRepository subscriptionUserRepository;

    public Map<String, String> createUser(CreateUserRequest request) {
        Map<String, String> response = new HashMap<>();
        subscriptionUserRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
            throw new IllegalStateException("Email already exists");
        });
        SubscriptionUser user = SubscriptionUser.builder()
                .name(request.getName()).roles(request.getRoles())
                .email(request.getEmail()).phone(request.getPhone())
                .country(request.getCountry()).build();
        subscriptionUserRepository.save(user);
        response.put("message", "User created successfully");
        return response;
    }

    public List<SubscriptionUser> fetchUsers() {
        return subscriptionUserRepository.findAll();
    }

    public SubscriptionUser fetchUserById(Long userId) {
        return subscriptionUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Map<String, String> updateUser(Long userId, UpdateUserRequest request) {
        Map<String, String> response = new HashMap<>();
        SubscriptionUser user = subscriptionUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (request.getName() != null) user.setName(request.getName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getCountry() != null) user.setCountry(request.getCountry());
        subscriptionUserRepository.save(user);
        response.put("message", "User updated successfully");
        return response;
    }
}
