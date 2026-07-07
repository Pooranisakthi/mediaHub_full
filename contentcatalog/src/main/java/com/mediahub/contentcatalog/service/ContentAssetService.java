package com.mediahub.contentcatalog.service;

import com.mediahub.contentcatalog.entity.ContentAsset;
import com.mediahub.contentcatalog.repository.ContentAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentAssetService {

    @Autowired
    ContentAssetRepository contentAssetRepository;

    // POST
    public String createContent(ContentAsset contentAsset) {
        contentAsset.setStatus("Draft");
        contentAssetRepository.save(contentAsset);
        return "Content created successfully";
    }

    // GET
    public List<ContentAsset> getAllContents() {
        return contentAssetRepository.findAll();
    }

    public ContentAsset getContentById(int contentId) {
        return contentAssetRepository.findById(contentId).orElse(null);
    }

    // PUT
    public String updateContent(int contentId, ContentAsset contentAsset) {
        ContentAsset existing = contentAssetRepository.findById(contentId).orElse(null);
        if (existing == null) return "Content not found";
        existing.setTitle(contentAsset.getTitle());
        existing.setGenre(contentAsset.getGenre());
        existing.setLanguage(contentAsset.getLanguage());
        existing.setSynopsis(contentAsset.getSynopsis());
        existing.setFilePath(contentAsset.getFilePath());
        existing.setThumbnailPath(contentAsset.getThumbnailPath());
        contentAssetRepository.save(existing);
        return "Content updated successfully";
    }

    public String updateContentStatus(int contentId, String status) {
        ContentAsset existing = contentAssetRepository.findById(contentId).orElse(null);
        if (existing == null) return "Content not found";
        existing.setStatus(status);
        contentAssetRepository.save(existing);
        return "Status updated successfully";
    }

    // DELETE
    public String deleteContent(int contentId) {
        ContentAsset existing = contentAssetRepository.findById(contentId).orElse(null);
        if (existing == null) return "Content not found";
        if (!existing.getStatus().equals("Draft")) return "Content can only be deleted when status is Draft";
        contentAssetRepository.deleteById(contentId);
        return "Content deleted successfully";
    }
}