package com.mediahub.contentcatalog.service;

import com.mediahub.contentcatalog.entity.ContentTag;
import com.mediahub.contentcatalog.repository.ContentTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentTagService {

    @Autowired
    ContentTagRepository contentTagRepository;

    // POST
    public String addTag(ContentTag contentTag) {
        contentTagRepository.save(contentTag);
        return "Tag added successfully";
    }

    // GET
    public List<ContentTag> getTagsByContentId(int contentId) {
        return contentTagRepository.findByContentId(contentId);
    }

    // DELETE
    public String removeTag(int tagId) {
        ContentTag existing = contentTagRepository.findById(tagId).orElse(null);
        if (existing == null) return "Tag not found";
        contentTagRepository.deleteById(tagId);
        return "Tag removed successfully";
    }
}