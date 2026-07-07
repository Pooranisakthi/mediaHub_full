package com.mediahub.editorial.repository;

import com.mediahub.editorial.model.ContentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentCollectionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // API 7 — Save new collection
    public int save(ContentCollection collection) {
        String contentIDsStr = collection.getContentIDs()
                .stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
        String sql = "INSERT INTO ContentCollection " +
                     "(Name, Category, ContentIDs, " +
                     "PublishDate, ExpiryDate, Status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                collection.getName(),
                collection.getCategory(),
                contentIDsStr,
                collection.getPublishDate(),
                collection.getExpiryDate(),
                collection.getStatus());
    }

    // API 8 — Get all collections
    public List<ContentCollection> findAll() {
        String sql = "SELECT * FROM ContentCollection";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ContentCollection c = new ContentCollection();
            c.setCollectionID(rs.getInt("CollectionID"));
            c.setName(rs.getString("Name"));
            c.setCategory(rs.getString("Category"));
            c.setStatus(rs.getString("Status"));
            return c;
        });
    }

    // API 9 — Get collection by ID
    public ContentCollection findById(int collectionID) {
        String sql = "SELECT * FROM ContentCollection " +
                     "WHERE CollectionID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ContentCollection c = new ContentCollection();
            c.setCollectionID(rs.getInt("CollectionID"));
            c.setName(rs.getString("Name"));
            c.setCategory(rs.getString("Category"));
            c.setStatus(rs.getString("Status"));
            return c;
        }, collectionID);
    }

    // API 10 — Update full collection
    public int update(int collectionID,
                      ContentCollection collection) {
        String contentIDsStr = collection.getContentIDs()
                .stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
        String sql = "UPDATE ContentCollection SET " +
                     "Name = ?, Category = ?, ContentIDs = ?, " +
                     "PublishDate = ?, ExpiryDate = ? " +
                     "WHERE CollectionID = ?";
        return jdbcTemplate.update(sql,
                collection.getName(),
                collection.getCategory(),
                contentIDsStr,
                collection.getPublishDate(),
                collection.getExpiryDate(),
                collectionID);
    }

    // API 11 — Expire collection
    public int updateStatus(int collectionID, String status) {
        String sql = "UPDATE ContentCollection " +
                     "SET Status = ? WHERE CollectionID = ?";
        return jdbcTemplate.update(sql, status, collectionID);
    }

    // API 12 — Delete collection
    public int delete(int collectionID) {
        String sql = "DELETE FROM ContentCollection " +
                     "WHERE CollectionID = ?";
        return jdbcTemplate.update(sql, collectionID);
    }

    // Check status before delete
    public String findStatusById(int collectionID) {
        String sql = "SELECT Status FROM ContentCollection " +
                     "WHERE CollectionID = ?";
        return jdbcTemplate.queryForObject(sql,
                String.class, collectionID);
    }
}