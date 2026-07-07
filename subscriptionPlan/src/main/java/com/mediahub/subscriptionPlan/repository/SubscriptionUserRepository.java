package com.mediahub.subscriptionPlan.repository;

import com.mediahub.subscriptionPlan.model.SubscriptionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionUserRepository extends JpaRepository<SubscriptionUser, Long> {
    Optional<SubscriptionUser> findByEmail(String email);
}
