package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import global.coda.hospitalmanagementsystem.constant.ApplicationDaoConstant;

/**
 * connection establishment class.
 *
 * @author CHANDRALEKA
 *
 */
public class DaoConnection {
  private static final ResourceBundle DAO_MESSAGES_BUNDLE = ResourceBundle
      .getBundle(ApplicationDaoConstant.DAO_MESSAGES);
  private static Connection connection = null;

  /**
   * method to get connection.
   *
   * @return connection object
   */
  public static Connection getConnection() {
    try {
      if (connection == null) {

        connection = DriverManager.getConnection(
            DAO_MESSAGES_BUNDLE.getString(ApplicationDaoConstant.CONNECTION_PATH),
            DAO_MESSAGES_BUNDLE.getString(ApplicationDaoConstant.ROOT_NAME),
            DAO_MESSAGES_BUNDLE.getString(ApplicationDaoConstant.PASSWORD));
        connection.setAutoCommit(false);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  /**
   * method to close connection.
   *
   * @param connection to close
   */
  public static void closeConnection(Connection connection) {
    try {
      if (!connection.isClosed()) {
        connection.close();
        connection = null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
