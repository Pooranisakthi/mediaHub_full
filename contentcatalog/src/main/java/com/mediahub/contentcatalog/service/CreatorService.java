package com.mediahub.contentcatalog.service;

import com.mediahub.contentcatalog.entity.Creator;
import com.mediahub.contentcatalog.repository.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorService {

    @Autowired
    CreatorRepository creatorRepository;

    public String createCreator(Creator creator) {
        creator.setStatus("PendingReview");
        creatorRepository.save(creator);
        return "Creator created successfully";
    }

    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }

    public Creator getCreatorById(int creatorId) {
        return creatorRepository.findById(creatorId).orElse(null);
    }

    public String updateCreator(int creatorId, Creator creator) {
        Creator existing = creatorRepository.findById(creatorId).orElse(null);
        if (existing == null) return "Creator not found";
        existing.setDisplayName(creator.getDisplayName());
        existing.setGenre(creator.getGenre());
        existing.setCountry(creator.getCountry());
        existing.setRoyaltyTier(creator.getRoyaltyTier());
        existing.setBankAccountRef(creator.getBankAccountRef());
        creatorRepository.save(existing);
        return "Creator updated successfully";
    }

    public String updateCreatorStatus(int creatorId, String status) {
        Creator existing = creatorRepository.findById(creatorId).orElse(null);
        if (existing == null) return "Creator not found";
        existing.setStatus(status);
        creatorRepository.save(existing);
        return "Status updated successfully";
    }
}