package com.cscb845f89497.sqlinjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AccountDAO {

    private final DataSource dataSource;
    private final EntityManager em;

    public AccountDAO(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    public List<AccountDTO> unsafeFindAccountsByUsernameAndPassword(String username, String password) {

        String sql = "select "
                + "username,password,customer_id,acc_number,branch_id,balance from Accounts where username = '"
                + username + "' and password =  '"
                + password + "'";

        try (Connection c = dataSource.getConnection();
                ResultSet rs = c.createStatement()
                        .executeQuery(sql)) {
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                AccountDTO acc = AccountDTO.builder()
                        .customerId(rs.getString("customer_id"))
                        .branchId(rs.getString("branch_id"))
                        .accNumber(rs.getString("acc_number"))
                        .balance(rs.getBigDecimal("balance"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();

                accounts.add(acc);
            }

            return accounts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<AccountDTO> unsafeFindAccountsByCustomerId(String customerId) {

        String sql = "select "
                + "username,password,customer_id,acc_number,branch_id,balance from Accounts where customer_id = '"
                + customerId + "'";

        try (Connection c = dataSource.getConnection();
                ResultSet rs = c.createStatement()
                        .executeQuery(sql)) {
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                AccountDTO acc = AccountDTO.builder()
                        .customerId(rs.getString("customer_id"))
                        .branchId(rs.getString("branch_id"))
                        .accNumber(rs.getString("acc_number"))
                        .balance(rs.getBigDecimal("balance"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();

                accounts.add(acc);
            }

            return accounts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<AccountDTO> safeFindAccountsByCustomerId(String customerId) {

        String sql = "select customer_id, branch_id,acc_number,balance from Accounts where customer_id = ?";

        try (Connection c = dataSource.getConnection();
                PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, customerId);
            ResultSet rs = p.executeQuery();
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                AccountDTO acc = AccountDTO.builder()
                        .customerId(rs.getString("customerId"))
                        .branchId(rs.getString("branch_id"))
                        .accNumber(rs.getString("acc_number"))
                        .balance(rs.getBigDecimal("balance"))
                        .build();

                accounts.add(acc);
            }
            return accounts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<AccountDTO> safeFindAccountsByUsernameAndPassword(String username, String password) {

        String sql = "select customer_id, branch_id,acc_number,balance from Accounts where username = ? and password = ?";

        try (Connection c = dataSource.getConnection();
                PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, username);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                AccountDTO acc = AccountDTO.builder()
                        .customerId(rs.getString("customer_id"))
                        .branchId(rs.getString("branch_id"))
                        .accNumber(rs.getString("acc_number"))
                        .balance(rs.getBigDecimal("balance"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();

                accounts.add(acc);
            }

            return accounts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
