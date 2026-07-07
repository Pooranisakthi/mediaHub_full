package com.mediahub.contentcatalog.controller;

import com.mediahub.contentcatalog.entity.ContentAsset;
import com.mediahub.contentcatalog.service.ContentAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediahub/contentCatalog/contentAsset")
public class ContentAssetController {

    @Autowired
    ContentAssetService contentAssetService;

    // POST
    @PostMapping("/createContent")
    public ResponseEntity<String> createContent(@RequestBody ContentAsset contentAsset) {
        return ResponseEntity.status(201).body(contentAssetService.createContent(contentAsset));
    }

    // GET
    @GetMapping("/fetchContents")
    public ResponseEntity<List<ContentAsset>> getAllContents() {
        return ResponseEntity.ok(contentAssetService.getAllContents());
    }

    @GetMapping("/fetchContentById/{contentId}")
    public ResponseEntity<ContentAsset> getContentById(@PathVariable int contentId) {
        return ResponseEntity.ok(contentAssetService.getContentById(contentId));
    }

    // PUT
    @PutMapping("/updateContent/{contentId}")
    public ResponseEntity<String> updateContent(@PathVariable int contentId, @RequestBody ContentAsset contentAsset) {
        return ResponseEntity.ok(contentAssetService.updateContent(contentId, contentAsset));
    }

    @PutMapping("/updateContentStatus/{contentId}")
    public ResponseEntity<String> updateContentStatus(@PathVariable int contentId, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(contentAssetService.updateContentStatus(contentId, body.get("status")));
    }

    // DELETE
    @DeleteMapping("/deleteContent/{contentId}")
    public ResponseEntity<String> deleteContent(@PathVariable int contentId) {
        return ResponseEntity.status(200).body(contentAssetService.deleteContent(contentId));
    }
}