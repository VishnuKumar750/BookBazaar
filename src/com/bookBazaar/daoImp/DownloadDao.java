package com.bookBazaar.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookBazaar.daoInterface.IDownloadDao;
import com.bookBazaar.entity.Download;
import com.bookBazaar.utility.DatabaseUtil;

public class DownloadDao implements IDownloadDao {

    public List<Download> getAllDownloadLogs() {
        List<Download> logs = new ArrayList<>();
        String query = "SELECT * FROM Download"; // Assuming a table named DownloadLog

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Download log = new Download(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("book_id"),
                    rs.getDate("download_date")
                );
                logs.add(log);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving download logs: " + e.getMessage());
        }
        return logs;
    }

    public List<Download> getDownloadLogsByUserId(int userId) {
        List<Download> logs = new ArrayList<>();
        String query = "SELECT * FROM Download WHERE user_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Download log = new Download(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("download_date")
                    );
                    logs.add(log);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving download logs: " + e.getMessage());
        }
        return logs;
    }
}
