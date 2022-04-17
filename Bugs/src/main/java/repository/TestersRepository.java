package repository;

import model.Tester;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TestersRepository implements Repository<Tester, Long> {

    private final JdbcUtils dbUtils;

    public TestersRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Tester element) {

    }

    @Override
    public void delete(Tester element) {

    }

    @Override
    public void update(Tester element) {
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?")) {
            preparedStatement.setString(1, element.getPassword());
            preparedStatement.setLong(2, element.getId());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public Tester findOne(Long elementId) {
        return null;
    }

    @Override
    public Iterable<Tester> findAll() {
        return null;
    }


    public Tester findOneByUsernameAndPassword(String username, String password) {
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new RuntimeException("Element not found!");
                }
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("full_name");

                String type = resultSet.getString("type");
                if (! type.equals("tester")) {
                    throw new RuntimeException("Element not found!");
                }

                Tester tester = new Tester(name, username, password);
                tester.setId(id);
                return tester;
            }

        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

}
