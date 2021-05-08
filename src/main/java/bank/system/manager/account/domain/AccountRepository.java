package bank.system.manager.account.domain;

import bank.system.manager.account.domain.model.Account;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final String URL = "jdbc:postgresql://localhost/bankdb";
    private final String USER = "postgres";
    private final String PASSWORD = "Pass1234";
    private final String DRIVER_NAME = "org.postgresql.Driver";

    public List<Account> findAll() {
        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            List<Account> accounts = new ArrayList<>();
            String query = "select id,sum,owner_id from accounts";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("accid");
                double sum = resultSet.getDouble("sum");
                long owner_id = resultSet.getLong("owner_id");
                accounts.add(new Account(id, sum, owner_id));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return accounts;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Account> findById(long id) {
        try {
            Class.forName(DRIVER_NAME);
            Optional<Account> optionalAccount = Optional.empty();
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "select * from Account where id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long sum = resultSet.getLong("sum");
                long owner_id = resultSet.getLong("owner_id");

                Account account=new Account(id,sum,owner_id);
                optionalAccount=Optional.of(account);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

            return optionalAccount;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(Account account) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "insert  into accounts(sum,owner_id,created_by,created_date)" +
                    "values (?,?,?,?) returning accid";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, account.getSum());
            preparedStatement.setLong(2, account.getOwnerId());
            preparedStatement.setLong(3, account.getCreatedBy());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(account.getCreatedDate()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long id = resultSet.getLong(1);

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return id;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(long id, long deleteBy, LocalDateTime deletedDate) {

        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "update customers SET is_deleted=cast(? as bit), deleted_by=?, deleted_date=? " +
                    "where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "1");
            preparedStatement.setLong(2, deleteBy);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(deletedDate));
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
