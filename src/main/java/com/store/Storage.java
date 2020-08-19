package com.store;

import com.model.Seat;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Storage {

    private final BasicDataSource pool = new BasicDataSource();

    public static Storage getInstance() {
        return Holder.storage;
    }

    private static class Holder {
        private final static Storage storage = new Storage();
    }

    private Storage() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("cinema/db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    public List<Seat> getHall() {
        List<Seat> data = new ArrayList<>();
        String query = "SELECT S.id AS id, S.row AS row, S.number AS number, S.price AS price, A.id AS accId " +
                "FROM seats AS S LEFT JOIN accounts AS A ON S.id = A.seatId " +
                "ORDER BY row, id";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Seat s = Seat.of(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("number"),
                            it.getDouble("price"),
                            it.getInt("accId"));
                    data.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Seat getSeat(int id) {
        String query = "SELECT S.id AS id, S.row AS row, S.number AS number, S.price AS price, A.id AS accId " +
                "FROM seats AS S LEFT JOIN accounts AS A ON S.id = A.seatId " +
                "WHERE S.id = (?)" +
                "ORDER BY row, id";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    Seat s = Seat.of(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("number"),
                            it.getDouble("price"),
                            it.getInt("accId"));
                    return s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerAcc(String name, String phone, int seatId) {
        String query = "INSERT INTO accounts(name, phone, seatId) VALUES (?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setInt(3, seatId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
