package net.alepuzio.spring.batch;

import java.sql.*;

public class DerbyTest {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DerbyTest() throws Exception {
        try {
//jdbc:derby://localhost:1527/c:\tool\my_spring_batch
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connect = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/my_spring_batch;create=true");
            PreparedStatement statement = connect
                    .prepareStatement("SELECT * from SCHEMA1.USERS");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("name");
                String number = resultSet.getString("number");
                System.out.println("User: " + user);
                System.out.println("ID: " + number);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        DerbyTest dao = new DerbyTest();
    }

}