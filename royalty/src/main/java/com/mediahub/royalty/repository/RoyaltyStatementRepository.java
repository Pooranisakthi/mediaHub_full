package com.mediahub.royalty.repository;

import com.mediahub.royalty.model.RoyaltyStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoyaltyStatementRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoyaltyStatementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // API 6 — Save new statement
    public int save(RoyaltyStatement statement) {
        String sql = "INSERT INTO RoyaltyStatement " +
                     "(CreatorID, Period, TotalViews, TotalRevenue, RoyaltyAmount, Status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                statement.getCreatorID(),
                statement.getPeriod(),
                statement.getTotalViews(),
                statement.getTotalRevenue(),
                statement.getRoyaltyAmount(),
                statement.getStatus());
    }

    // API 7 — Get all statements
    public List<RoyaltyStatement> findAll() {
        String sql = "SELECT * FROM RoyaltyStatement";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RoyaltyStatement s = new RoyaltyStatement();
            s.setStatementID(rs.getInt("StatementID"));
            s.setCreatorID(rs.getInt("CreatorID"));
            s.setPeriod(rs.getString("Period"));
            s.setTotalViews(rs.getLong("TotalViews"));
            s.setTotalRevenue(rs.getDouble("TotalRevenue"));
            s.setRoyaltyAmount(rs.getDouble("RoyaltyAmount"));
            s.setStatus(rs.getString("Status"));
            return s;
        });
    }

    // API 8 — Get statement by ID
    public RoyaltyStatement findById(int statementID) {
        String sql = "SELECT * FROM RoyaltyStatement WHERE StatementID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            RoyaltyStatement s = new RoyaltyStatement();
            s.setStatementID(rs.getInt("StatementID"));
            s.setCreatorID(rs.getInt("CreatorID"));
            s.setPeriod(rs.getString("Period"));
            s.setTotalViews(rs.getLong("TotalViews"));
            s.setTotalRevenue(rs.getDouble("TotalRevenue"));
            s.setRoyaltyAmount(rs.getDouble("RoyaltyAmount"));
            s.setStatus(rs.getString("Status"));
            return s;
        }, statementID);
    }

    // API 9, 10 — Update status
    public int updateStatus(int statementID, String status) {
        String sql = "UPDATE RoyaltyStatement SET Status = ? WHERE StatementID = ?";
        return jdbcTemplate.update(sql, status, statementID);
    }

    // Check status before transition
    public String findStatusById(int statementID) {
        String sql = "SELECT Status FROM RoyaltyStatement WHERE StatementID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, statementID);
    }
}
