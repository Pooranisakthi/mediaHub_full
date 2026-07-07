package com.mediahub.editorial.service;

import com.mediahub.editorial.model.ContentCollection;
import com.mediahub.editorial.repository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentCollectionService {

    @Autowired
    private ContentCollectionRepository repository;

    // API 7 — Create collection
    public Map<String, Object> createCollection(
            ContentCollection collection) {
        Map<String, Object> response = new HashMap<>();
        if (collection.getName() == null
                || collection.getName().isEmpty()) {
            response.put("error", "Name is required");
            response.put("statusCode", 400);
            return response;
        }
        String cat = collection.getCategory();
        if (cat == null || (!cat.equals("Featured")
                && !cat.equals("Trending")
                && !cat.equals("Curated")
                && !cat.equals("New"))) {
            response.put("error",
                "Category must be Featured Trending Curated or New");
            response.put("statusCode", 400);
            return response;
        }
        if (collection.getContentIDs() == null
                || collection.getContentIDs().isEmpty()) {
            response.put("error", "ContentIDs are required");
            response.put("statusCode", 400);
            return response;
        }
        if (collection.getPublishDate() == null
                || collection.getExpiryDate() == null) {
            response.put("error", "Dates are required");
            response.put("statusCode", 400);
            return response;
        }
        collection.setStatus("Scheduled");
        int result = repository.save(collection);
        if (result > 0) {
            response.put("name",        collection.getName());
            response.put("category",    collection.getCategory());
            response.put("contentIDs",  collection.getContentIDs());
            response.put("publishDate", collection.getPublishDate());
            response.put("expiryDate",  collection.getExpiryDate());
            response.put("status",      "Scheduled");
            response.put("message",     "Collection created successfully.");
            response.put("statusCode",  201);
        } else {
            response.put("error", "Failed to create collection");
            response.put("statusCode", 500);
        }
        return response;
    }

    // API 8 — Get all collections
    public List<ContentCollection> getAllCollections() {
        return repository.findAll();
    }

    // API 9 — Get collection by ID
    public Map<String, Object> getCollectionById(int collectionID) {
        Map<String, Object> response = new HashMap<>();
        try {
            ContentCollection c = repository.findById(collectionID);
            response.put("collection", c);
            response.put("statusCode", 200);
        } catch (Exception e) {
            response.put("error",
                "Collection not found with ID: " + collectionID);
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 10 — Update full collection
    public Map<String, Object> updateCollection(
            int collectionID, ContentCollection collection) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.update(
                    collectionID, collection);
            if (result > 0) {
                response.put("collectionID", collectionID);
                response.put("message",
                    "Collection updated successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Collection not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Collection not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 11 — Expire collection
    public Map<String, Object> expireCollection(int collectionID) {
        Map<String, Object> response = new HashMap<>();
        try {
            int result = repository.updateStatus(
                    collectionID, "Expired");
            if (result > 0) {
                response.put("collectionID", collectionID);
                response.put("status",       "Expired");
                response.put("message",
                    "Collection expired successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Collection not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Collection not found");
            response.put("statusCode", 404);
        }
        return response;
    }

    // API 12 — Delete collection
    public Map<String, Object> deleteCollection(int collectionID) {
        Map<String, Object> response = new HashMap<>();
        try {
            String status = repository.findStatusById(collectionID);
            if (status.equals("Active")) {
                response.put("error",
                    "Cannot delete Active collection. Expire it first.");
                response.put("statusCode", 400);
                return response;
            }
            int result = repository.delete(collectionID);
            if (result > 0) {
                response.put("collectionID", collectionID);
                response.put("message",
                    "Collection deleted successfully.");
                response.put("statusCode", 200);
            } else {
                response.put("error", "Collection not found");
                response.put("statusCode", 404);
            }
        } catch (Exception e) {
            response.put("error", "Collection not found");
            response.put("statusCode", 404);
        }
        return response;
    }
}