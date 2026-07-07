package com.mediahub.editorial.repository;

import com.mediahub.editorial.model.EditorialReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EditorialReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // API 1 — Save new review
    public int save(EditorialReview review) {
        String sql = "INSERT INTO EditorialReview " +
                     "(ContentID, ReviewerID, SubmissionDate, Status) " +
                     "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                review.getContentID(),
                review.getReviewerID(),
                review.getSubmissionDate(),
                review.getStatus());
    }

    // API 2 — Get all reviews
    public List<EditorialReview> findAll() {
        String sql = "SELECT * FROM EditorialReview";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EditorialReview r = new EditorialReview();
            r.setReviewID(rs.getInt("ReviewID"));
            r.setContentID(rs.getInt("ContentID"));
            r.setReviewerID(rs.getInt("ReviewerID"));
            r.setSubmissionDate(rs.getDate("SubmissionDate"));
            r.setReviewDate(rs.getDate("ReviewDate"));
            r.setDecision(rs.getString("Decision"));
            r.setRemarks(rs.getString("Remarks"));
            r.setStatus(rs.getString("Status"));
            return r;
        });
    }

    // API 3 — Get review by ID
    public EditorialReview findById(int reviewID) {
        String sql = "SELECT * FROM EditorialReview " +
                     "WHERE ReviewID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            EditorialReview r = new EditorialReview();
            r.setReviewID(rs.getInt("ReviewID"));
            r.setContentID(rs.getInt("ContentID"));
            r.setReviewerID(rs.getInt("ReviewerID"));
            r.setSubmissionDate(rs.getDate("SubmissionDate"));
            r.setReviewDate(rs.getDate("ReviewDate"));
            r.setDecision(rs.getString("Decision"));
            r.setRemarks(rs.getString("Remarks"));
            r.setStatus(rs.getString("Status"));
            return r;
        }, reviewID);
    }

    // API 4, 5, 6 — Update decision
    public int updateDecision(int reviewID, String decision,
                              String remarks, String status) {
        String sql = "UPDATE EditorialReview SET " +
                     "Decision = ?, Remarks = ?, " +
                     "ReviewDate = NOW(), Status = ? " +
                     "WHERE ReviewID = ?";
        return jdbcTemplate.update(sql,
                decision, remarks, status, reviewID);
    }
}