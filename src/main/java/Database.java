import org.apache.commons.lang3.StringUtils;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;
import java.math.BigDecimal;

public class Database {
    static Connection conn;
    public static Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb", username, password);
    }

    public static void initialize(String username, String password) throws SQLException {
        conn = getConnection(username, password);
    }

    public static LinkedList<LinkedList<Object>> getData(String table) throws SQLException {
        LinkedList<LinkedList<Object>> rows = new LinkedList<>();
        String sql = "SELECT * FROM " + table;
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        try {
            while (result.next()) {
                LinkedList<Object> row = new LinkedList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object res = result.getObject(i);
                    if (res instanceof BigDecimal) {
                        res = ((BigDecimal) res).intValue();
                    } else if (res instanceof Integer) {
                        res = (Integer) res;
                    }
                    row.add(res);
                }
                rows.add(row);
            }
        } finally {
            result.close();
            statement.close();
        }
        return rows;
    }

    public static void addData(String table, String... objects) throws SQLException {
        String sql = "INSERT INTO " + table + " VALUES(" + StringUtils.repeat("?", ",", objects.length) + ")";
        PreparedStatement statement = conn.prepareStatement(sql);
        try {
            for (int i = 0; i < objects.length; i++) {
                statement.setString(i + 1, objects[i]);
            }
            statement.executeUpdate();
        } finally {
            statement.close();
        }
    }

    public static void modifyData(String table, String setClause, String whereClause) throws SQLException {
        String sql = "UPDATE " + table + " SET " + setClause + " WHERE " + whereClause;
        Statement statement = conn.createStatement();
        try {
            statement.executeUpdate(sql);
        } finally {
            statement.close();
        }
    }

    public static void deleteData(String table, String whereClause) {
        try {
            String sql = "DELETE FROM " + table + " WHERE " + whereClause;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Could not delete the row. ID is being used in another table!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void close() throws SQLException {
        conn.close();
    }

    public Connection getConn() {
        return conn;
    }
}
