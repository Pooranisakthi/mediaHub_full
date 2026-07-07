package com.mediahub.editorial.repository;

import com.mediahub.editorial.model.PublicationSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublicationScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // API 13 — Save new schedule
    public int save(PublicationSchedule schedule) {
        String sql = "INSERT INTO PublicationSchedule " +
                     "(ContentID, PublishDateTime, " +
                     "ExpiryDateTime, Territory, Status) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                schedule.getContentID(),
                schedule.getPublishDateTime(),
                schedule.getExpiryDateTime(),
                schedule.getTerritory(),
                schedule.getStatus());
    }

    // API 14 — Get all schedules
    public List<PublicationSchedule> findAll() {
        String sql = "SELECT * FROM PublicationSchedule";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PublicationSchedule s = new PublicationSchedule();
            s.setScheduleID(rs.getInt("ScheduleID"));
            s.setContentID(rs.getInt("ContentID"));
            s.setPublishDateTime(rs.getTimestamp("PublishDateTime"));
            s.setExpiryDateTime(rs.getTimestamp("ExpiryDateTime"));
            s.setTerritory(rs.getString("Territory"));
            s.setStatus(rs.getString("Status"));
            return s;
        });
    }

    // API 15 — Get schedule by ID
    public PublicationSchedule findById(int scheduleID) {
        String sql = "SELECT * FROM PublicationSchedule " +
                     "WHERE ScheduleID = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            PublicationSchedule s = new PublicationSchedule();
            s.setScheduleID(rs.getInt("ScheduleID"));
            s.setContentID(rs.getInt("ContentID"));
            s.setPublishDateTime(rs.getTimestamp("PublishDateTime"));
            s.setExpiryDateTime(rs.getTimestamp("ExpiryDateTime"));
            s.setTerritory(rs.getString("Territory"));
            s.setStatus(rs.getString("Status"));
            return s;
        }, scheduleID);
    }

    // API 16, 17 — Update status
    public int updateStatus(int scheduleID, String status) {
        String sql = "UPDATE PublicationSchedule " +
                     "SET Status = ? WHERE ScheduleID = ?";
        return jdbcTemplate.update(sql, status, scheduleID);
    }

    // API 16 — Update status with reason
    public int updateStatusWithReason(int scheduleID,
                                      String status,
                                      String reason) {
        String sql = "UPDATE PublicationSchedule " +
                     "SET Status = ?, Territory = ? " +
                     "WHERE ScheduleID = ?";
        return jdbcTemplate.update(sql, status, reason, scheduleID);
    }

    // API 18 — Delete schedule
    public int delete(int scheduleID) {
        String sql = "DELETE FROM PublicationSchedule " +
                     "WHERE ScheduleID = ?";
        return jdbcTemplate.update(sql, scheduleID);
    }

    // Check status before delete
    public String findStatusById(int scheduleID) {
        String sql = "SELECT Status FROM PublicationSchedule " +
                     "WHERE ScheduleID = ?";
        return jdbcTemplate.queryForObject(sql,
                String.class, scheduleID);
    }
}