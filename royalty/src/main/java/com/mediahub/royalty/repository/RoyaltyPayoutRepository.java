package com.mediahub.royalty.repository;

import com.mediahub.royalty.model.RoyaltyPayout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoyaltyPayoutRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoyaltyPayoutRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // API 11 — Save new payout
    public int save(RoyaltyPayout payout) {
        String sql = "INSERT INTO RoyaltyPayout " +
                     "(StatementID, CreatorID, Amount, PayoutDate, Method, Status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                payout.getStatementID(),
                payout.getCreatorID(),
                payout.getAmount(),
                payout.getPayoutDate(),
                payout.getMethod(),
                payout.getStatus());
    }

    // API 12 — Get all payouts
    public List<RoyaltyPayout> findAll() {
        String sql = "SELECT * FROM RoyaltyPayout";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RoyaltyPayout p = new RoyaltyPayout();
            p.setPayoutID(rs.getInt("PayoutID"));
            p.setStatementID(rs.getInt("StatementID"));
            p.setCreatorID(rs.getInt("CreatorID"));
            p.setAmount(rs.getDouble("Amount"));
            p.setPayoutDate(rs.getDate("PayoutDate"));
            p.setMethod(rs.getString("Method"));
            p.setStatus(rs.getString("Status"));
            return p;
        });
    }

    // API 13 — Get payout by ID
    public RoyaltyPayout findById(int payoutID) {
        String sql = "SELECT * FROM RoyaltyPayout WHERE PayoutID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            RoyaltyPayout p = new RoyaltyPayout();
            p.setPayoutID(rs.getInt("PayoutID"));
            p.setStatementID(rs.getInt("StatementID"));
            p.setCreatorID(rs.getInt("CreatorID"));
            p.setAmount(rs.getDouble("Amount"));
            p.setPayoutDate(rs.getDate("PayoutDate"));
            p.setMethod(rs.getString("Method"));
            p.setStatus(rs.getString("Status"));
            return p;
        }, payoutID);
    }

    // API 14, 15 — Update status
    public int updateStatus(int payoutID, String status) {
        String sql = "UPDATE RoyaltyPayout SET Status = ? WHERE PayoutID = ?";
        return jdbcTemplate.update(sql, status, payoutID);
    }

    // API 16 — Delete payout
    public int delete(int payoutID) {
        String sql = "DELETE FROM RoyaltyPayout WHERE PayoutID = ?";
        return jdbcTemplate.update(sql, payoutID);
    }

    // Check status before delete
    public String findStatusById(int payoutID) {
        String sql = "SELECT Status FROM RoyaltyPayout WHERE PayoutID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, payoutID);
    }
}
