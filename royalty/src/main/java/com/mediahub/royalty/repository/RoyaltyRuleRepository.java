package com.mediahub.royalty.repository;

import com.mediahub.royalty.model.RoyaltyRule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoyaltyRuleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoyaltyRuleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // API 1 — Save new rule
    public int save(RoyaltyRule rule) {
        String sql = "INSERT INTO RoyaltyRule " +
                     "(CreatorTier, RevenueSharePercent, MinimumPayoutThreshold, " +
                     "PayoutFrequency, EffectiveDate, Status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                rule.getCreatorTier(),
                rule.getRevenueSharePercent(),
                rule.getMinimumPayoutThreshold(),
                rule.getPayoutFrequency(),
                rule.getEffectiveDate(),
                rule.getStatus());
    }

    // API 2 — Get all rules
    public List<RoyaltyRule> findAll() {
        String sql = "SELECT * FROM RoyaltyRule";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RoyaltyRule r = new RoyaltyRule();
            r.setRuleID(rs.getInt("RuleID"));
            r.setCreatorTier(rs.getString("CreatorTier"));
            r.setRevenueSharePercent(rs.getDouble("RevenueSharePercent"));
            r.setMinimumPayoutThreshold(rs.getDouble("MinimumPayoutThreshold"));
            r.setPayoutFrequency(rs.getString("PayoutFrequency"));
            r.setEffectiveDate(rs.getDate("EffectiveDate"));
            r.setStatus(rs.getString("Status"));
            return r;
        });
    }

    // API 3 — Get rule by ID
    public RoyaltyRule findById(int ruleID) {
        String sql = "SELECT * FROM RoyaltyRule WHERE RuleID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            RoyaltyRule r = new RoyaltyRule();
            r.setRuleID(rs.getInt("RuleID"));
            r.setCreatorTier(rs.getString("CreatorTier"));
            r.setRevenueSharePercent(rs.getDouble("RevenueSharePercent"));
            r.setMinimumPayoutThreshold(rs.getDouble("MinimumPayoutThreshold"));
            r.setPayoutFrequency(rs.getString("PayoutFrequency"));
            r.setEffectiveDate(rs.getDate("EffectiveDate"));
            r.setStatus(rs.getString("Status"));
            return r;
        }, ruleID);
    }

    // API 4 — Update status
    public int updateStatus(int ruleID, String status) {
        String sql = "UPDATE RoyaltyRule SET Status = ? WHERE RuleID = ?";
        return jdbcTemplate.update(sql, status, ruleID);
    }

    // API 5 — Delete rule
    public int delete(int ruleID) {
        String sql = "DELETE FROM RoyaltyRule WHERE RuleID = ?";
        return jdbcTemplate.update(sql, ruleID);
    }

    // Check status before delete
    public String findStatusById(int ruleID) {
        String sql = "SELECT Status FROM RoyaltyRule WHERE RuleID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, ruleID);
    }
}
