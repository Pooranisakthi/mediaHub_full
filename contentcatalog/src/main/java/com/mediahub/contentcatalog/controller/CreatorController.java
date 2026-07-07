package com.mediahub.contentcatalog.controller;

import com.mediahub.contentcatalog.entity.Creator;
import com.mediahub.contentcatalog.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediahub/contentCatalog/creator")
public class CreatorController {

    @Autowired
    CreatorService creatorService;

    // POST
    @PostMapping("/createCreator")
    public ResponseEntity<String> createCreator(@RequestBody Creator creator) {
        return ResponseEntity.status(201).body(creatorService.createCreator(creator));
    }

    // GET
    @GetMapping("/fetchCreators")
    public ResponseEntity<List<Creator>> getAllCreators() {
        return ResponseEntity.ok(creatorService.getAllCreators());
    }

    @GetMapping("/fetchCreatorById/{creatorId}")
    public ResponseEntity<Creator> getCreatorById(@PathVariable int creatorId) {
        return ResponseEntity.ok(creatorService.getCreatorById(creatorId));
    }

    // PUT
    @PutMapping("/updateCreator/{creatorId}")
    public ResponseEntity<String> updateCreator(@PathVariable int creatorId, @RequestBody Creator creator) {
        return ResponseEntity.ok(creatorService.updateCreator(creatorId, creator));
    }

    @PutMapping("/updateCreatorStatus/{creatorId}")
    public ResponseEntity<String> updateCreatorStatus(@PathVariable int creatorId, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(creatorService.updateCreatorStatus(creatorId, body.get("status")));
    }
}