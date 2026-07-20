package com.jagath.dbviewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DatabaseController {

    @Autowired
    private DataSource dataSource;

    // Schemas MySQL ships with by default — usually not what you care about
    private static final List<String> SYSTEM_SCHEMAS = List.of(
            "information_schema", "mysql", "performance_schema", "sys"
    );

    @GetMapping("/api/databases")
    public Map<String, Object> listDatabases() {
        List<String> databases = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW DATABASES")) {

            while (rs.next()) {
                databases.add(rs.getString(1));
            }
            return Map.of("status", "success", "count", databases.size(), "databases", databases);

        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }

    @GetMapping("/api/databases/user")
    public Map<String, Object> listUserDatabases() {
        List<String> databases = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW DATABASES")) {

            while (rs.next()) {
                String db = rs.getString(1);
                if (!SYSTEM_SCHEMAS.contains(db.toLowerCase())) {
                    databases.add(db);
                }
            }
            return Map.of("status", "success", "count", databases.size(), "databases", databases);

        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }
}
