package org.example.orm;

import org.example.H2DataBase;
import org.example.logger.ConsoleLogger;
import org.example.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Logger log = ConsoleLogger.get();
    private final Map<Long, User> identityMap = new HashMap<>();

    private UserMapper() {
    }

    public static UserMapper create() {
        return new UserMapper();
    }

    public void close() {
        H2DataBase.disconnect();
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        Connection conn = H2DataBase.connect().getConnection();
        try (final PreparedStatement ps = conn.prepareStatement("SELECT id,login,password FROM users where id = ?;")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                conn.close();
                H2DataBase.disconnect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    public void update(User user) {
        Optional<User> oldUser = findById(user.getId());
        if (!oldUser.isEmpty()) {
            Connection conn = H2DataBase.connect().getConnection();
            try (final PreparedStatement ps = conn.prepareStatement("UPDATE users SET password = ? , login =? where id = ?;")) {
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getLogin());
                ps.setInt(3, user.getId());
                log.info("пользователь " + oldUser.get().getLogin() + " изменён на " + user.getLogin());
                ps.executeUpdate();
                identityMap.remove(oldUser.get());
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } finally {
                try {
                    conn.close();
                    H2DataBase.disconnect();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else log.info("не найден пользователь для изменения");
    }

    public void insert(User user) {
        if (findById(user.getId()).isEmpty()) {
            Connection conn = H2DataBase.connect().getConnection();
            try (final PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO users " +
                            "(id,login,password) VALUES (?,?,?);"
                    )) {
                ps.setInt(1, user.getId());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getPassword());
                ps.executeUpdate();

                log.info("пользователь " + user.getLogin() + " создан");
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } finally {
                try {
                    conn.close();
                    H2DataBase.disconnect();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else log.info("пользователь " + user.getLogin() + " уже существует");
    }

    public void delete(User user) {
        Optional<User> oldUser = findById(user.getId());
        if (!oldUser.isEmpty()) {
            if (user.getLogin().equals(oldUser.get().getLogin()) && user.getPassword().equals(oldUser.get().getPassword())) {
                Connection conn = H2DataBase.connect().getConnection();
                try (final PreparedStatement ps = conn.prepareStatement("DELETE FROM users where id = ?;")) {
                    ps.setInt(1, user.getId());
                    ps.executeUpdate();
                    log.info("пользователь " + user.getLogin() + " удалён");
                } catch (SQLException e) {
                    throw new IllegalStateException(e);
                } finally {
                    try {
                        conn.close();
                        H2DataBase.disconnect();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else log.info("нет с ником " + user.getLogin() + " пользователя для удаления");
        } else log.info("нет с ником " + user.getLogin() + " пользователя для удаления");
    }
}
