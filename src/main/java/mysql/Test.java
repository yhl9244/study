package mysql;

import java.sql.*;

public class Test {



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/department", "postgres", "root");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into public.student values (?, ?, ?)", new String[]{"name"});

        preparedStatement.setInt(1, 5);
        preparedStatement.setString(2, "zhangsan");
        preparedStatement.setString(3, "cccc");
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
