package repository;

import model.Programmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ProgrammersRepository implements Repository<Programmer, Long>{

    private final JdbcUtils dbUtils;

    public ProgrammersRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Programmer element) {

    }

    @Override
    public void delete(Programmer element) {

    }

    @Override
    public void update(Programmer element) {
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
    public Programmer findOne(Long elementId) {
        return null;
    }

    @Override
    public Iterable<Programmer> findAll() {
        return null;
    }

    public Programmer findOneByUsernameAndPassword(String username, String password) {
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
                if (! type.equals("programmer")) {
                    throw new RuntimeException("Element not found!");
                }

                Programmer programmer = new Programmer(name, username, password);
                programmer.setId(id);
                return programmer;
            }

        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

}
