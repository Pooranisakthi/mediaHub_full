package com.mediahub.subscriptionPlan;

import com.mediahub.subscriptionPlan.model.SubscriptionHistory;
import com.mediahub.subscriptionPlan.model.SubscriptionPlan;
import com.mediahub.subscriptionPlan.model.SubscriptionUser;
import com.mediahub.subscriptionPlan.model.UserSubscription;
import com.mediahub.subscriptionPlan.repository.SubscriptionHistoryRepository;
import com.mediahub.subscriptionPlan.repository.SubscriptionPlanRepository;
import com.mediahub.subscriptionPlan.repository.SubscriptionUserRepository;
import com.mediahub.subscriptionPlan.repository.UserSubscriptionRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("subscriptionDataLoader")
public class DataLoader implements CommandLineRunner {

    @Autowired private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired private SubscriptionUserRepository subscriptionUserRepository;
    @Autowired private UserSubscriptionRepository userSubscriptionRepository;
    @Autowired private SubscriptionHistoryRepository subscriptionHistoryRepository;

    Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        if (subscriptionPlanRepository.count() == 0) { insertPlans(); System.out.println("Subscription Plans inserted"); }
        if (subscriptionUserRepository.count() == 0) { insertUsers(); System.out.println("Subscription Users inserted"); }
        if (userSubscriptionRepository.count() == 0) { insertSubscriptions(); System.out.println("User Subscriptions inserted"); }
        if (subscriptionHistoryRepository.count() == 0) { insertHistories(); System.out.println("Subscription Histories inserted"); }
    }

    private void insertPlans() {
        String[] planNames = {"Free","Basic","Standard","Premium"};
        String[] billingCycles = {"Monthly","Quarterly","Annual"};
        String[] accessLevels = {"SD only","HD content","Full HD","4K HDR"};
        double[] prices = {0.00,4.99,8.99,14.99};
        List<SubscriptionPlan> plans = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            int idx = (i-1)%4;
            plans.add(SubscriptionPlan.builder().name(planNames[idx]+"_"+i).price(prices[idx])
                .billingCycle(billingCycles[i%3]).contentAccessLevel(accessLevels[idx])
                .maxDevices(faker.number().numberBetween(1,6)).downloadAllowed(faker.number().numberBetween(0,2)).status("Active").build());
        }
        subscriptionPlanRepository.saveAll(plans);
    }

    private void insertUsers() {
        String[] roles = {"Admin","User","Manager","Viewer"};
        String[] statuses = {"Active","Suspended","Inactive"};
        List<SubscriptionUser> users = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            users.add(SubscriptionUser.builder().name(faker.name().fullName()).roles(roles[i%4])
                .email("subuser"+i+"@mediahub.io")
                .phone("+91-"+faker.number().numberBetween(7000000000L,9999999999L))
                .country(faker.address().country()).status(statuses[i%3]).build());
        }
        subscriptionUserRepository.saveAll(users);
    }

    private void insertSubscriptions() {
        String[] renewalTypes = {"AutoRenew","Manual"};
        String[] statuses = {"Active","Expired","Cancelled"};
        List<UserSubscription> subscriptions = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            LocalDate start = LocalDate.of(2025,1,1).plusDays(i);
            subscriptions.add(UserSubscription.builder().userId((long)i).planId((long)((i%250)+1))
                .startDate(start).endDate(start.plusDays(30)).renewalType(renewalTypes[i%2]).status(statuses[i%3]).build());
        }
        userSubscriptionRepository.saveAll(subscriptions);
    }

    private void insertHistories() {
        String[] changeTypes = {"New","Upgrade","Downgrade","Renewal","Cancellation"};
        List<SubscriptionHistory> histories = new ArrayList<>();
        for (int i = 1; i <= 250; i++) {
            histories.add(SubscriptionHistory.builder().subscriptionId((long)i).userId((long)i)
                .fromPlanId(i==1?null:(long)((i-1)%250+1)).toPlanId((long)(i%250+1))
                .changeType(changeTypes[i%5]).changeDate(LocalDateTime.of(2025,1,1,9,0).plusDays(i))
                .remarks(faker.lorem().sentence()).build());
        }
        subscriptionHistoryRepository.saveAll(histories);
    }
}
