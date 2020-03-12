package DATABASEhw2;
import java.io.*;


import java.sql.*;
import java.time.Duration;
import java.time.Instant;

public class connector {
    private static final String url = "jdbc:postgresql://localhost:5433/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";
    public static Connection connection = null;
    public static String uidtest = "AUWHIxgZuL2h4svVLdUZaA";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connected to database");
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            Instant start = Instant.now();
           // select business_id from business where (is_open =1 and state = "AZ") order by review_count desc limit 10;
//
//            ResultSet resultSet = statement.executeQuery("select user_id\n" +
//                    "from review ");
            //ResultSet resultSet = statement.executeQuery(query.stillThere("AZ"));
//            ResultSet resultSet = statement.executeQuery(query.topReviews("PMDlKLd0Mxj0ngCpuUmE5Q"));
//            ResultSet resultSet = statement.executeQuery(query.averageRating(uidtest));
            ResultSet resultSet = statement.executeQuery(query.topBusinessInCity("Toronto", 200,10 ));

            //XgqVF5YKdQLCWDKiEkt0g

            Instant end = Instant.now();

            ResultSetMetaData thisdummyvar =resultSet.getMetaData();
            int number_of_the_fricken_columns = thisdummyvar.getColumnCount();
            while (resultSet.next()) {
                for (int count = 1; count <= number_of_the_fricken_columns; count++) {

                    String s = thisdummyvar.getColumnName(count);
                    System.out.print(s + ": " + resultSet.getString(count)+" ");
                }
                System.out.println(" ");
                //
            }
            System.out.println(Duration.between(start, end));
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
//    public static void main(String[] args) throws SQLException {
//        String state = "AZ";
//        callFunctions(query.stillThere(state));
//    }
}