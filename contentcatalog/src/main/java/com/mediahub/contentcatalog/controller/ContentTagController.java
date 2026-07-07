package com.mediahub.contentcatalog.controller;

import com.mediahub.contentcatalog.entity.ContentTag;
import com.mediahub.contentcatalog.service.ContentTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediahub/contentCatalog/contentTag")
public class ContentTagController {

    @Autowired
    ContentTagService contentTagService;

    // POST
    @PostMapping("/addTag")
    public ResponseEntity<String> addTag(@RequestBody ContentTag contentTag) {
        return ResponseEntity.status(201).body(contentTagService.addTag(contentTag));
    }

    // GET
    @GetMapping("/fetchTagsByContent/{contentId}")
    public ResponseEntity<List<ContentTag>> getTagsByContentId(@PathVariable int contentId) {
        return ResponseEntity.ok(contentTagService.getTagsByContentId(contentId));
    }

    // DELETE
    @DeleteMapping("/removeTag/{tagId}")
    public ResponseEntity<String> removeTag(@PathVariable int tagId) {
        return ResponseEntity.status(200).body(contentTagService.removeTag(tagId));
    }
}