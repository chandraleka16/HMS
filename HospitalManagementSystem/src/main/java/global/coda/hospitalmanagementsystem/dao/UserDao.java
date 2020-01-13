package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constant.ApplicationConstant;
import global.coda.hospitalmanagementsystem.constant.ApplicationDaoConstant;
import global.coda.hospitalmanagementsystem.model.User;

/**
 * class to do user dao operations.
 *
 * @author Chandraleka
 *
 */
public class UserDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
  private static final ResourceBundle DAO_MESSAGE_BUNDLE = ResourceBundle
      .getBundle(ApplicationDaoConstant.DAO_MESSAGES);
  private Connection connection = null;
  private PreparedStatement preStatement = null;
  private ResultSet resultSet = null;
  private int userId;

  private User user = new User();

  /**
   * method to insert a new user into db.
   *
   * @param userData to insert
   * @return user id
   * @throws SQLException when SQL query is wrong.
   */
  public int createUser(User userData) throws SQLException {
    int userId = 0;
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection.prepareStatement(
          DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSUI001),
          PreparedStatement.RETURN_GENERATED_KEYS);
      preStatement.setString(ApplicationConstant.NUMBER1, userData.getUserName());
      preStatement.setString(ApplicationConstant.NUMBER2, userData.getUserPassword());
      preStatement.setInt(ApplicationConstant.NUMBER3, userData.getUserAge());
      preStatement.setString(ApplicationConstant.NUMBER4, userData.getUserGender());
      preStatement.setString(ApplicationConstant.NUMBER5, userData.getUserMobileNumber());
      preStatement.setString(ApplicationConstant.NUMBER6, userData.getUserEmailId());
      preStatement.setString(ApplicationConstant.NUMBER7, userData.getUserAddressLine1());
      preStatement.setString(ApplicationConstant.NUMBER8, userData.getUserAddressLine2());
      preStatement.setString(ApplicationConstant.NUMBER9, userData.getUserAddressLine3());
      preStatement.setInt(ApplicationConstant.NUMBER10, userData.getUserRoleId());
      preStatement.executeUpdate();
      resultSet = preStatement.getGeneratedKeys();
      if (resultSet.next()) {
        userId = resultSet.getInt(ApplicationConstant.NUMBER1);
        return userId;
      }
      System.out.println(userId);
    } catch (Exception e) {
      connection.rollback();
      LOGGER.error(e.getMessage());

    }
    return userId;
  }

  /**
   * method to update user details in db.
   *
   * @param userData to update
   * @return result value
   */
  public int updateUser(User userData) {
    try {
      connection = DaoConnection.getConnection();

      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSUU001));
      preStatement.setString(ApplicationConstant.NUMBER1, userData.getUserName());
      preStatement.setString(ApplicationConstant.NUMBER2, userData.getUserPassword());
      preStatement.setInt(ApplicationConstant.NUMBER3, userData.getUserAge());
      preStatement.setString(ApplicationConstant.NUMBER4, userData.getUserGender());
      preStatement.setString(ApplicationConstant.NUMBER5, userData.getUserMobileNumber());
      preStatement.setString(ApplicationConstant.NUMBER6, userData.getUserEmailId());
      preStatement.setString(ApplicationConstant.NUMBER7, userData.getUserAddressLine1());
      preStatement.setString(ApplicationConstant.NUMBER8, userData.getUserAddressLine2());
      preStatement.setString(ApplicationConstant.NUMBER9, userData.getUserAddressLine3());
      preStatement.setInt(ApplicationConstant.NUMBER10, userData.getUserId());
      System.out.println(preStatement);
      int result = preStatement.executeUpdate();
      LOGGER.debug(String.valueOf(result));
      if (result == ApplicationConstant.NUMBER1) {
        connection.commit();
        return 1;
      } else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      return 0;
    }

  }

  /**
   * method to delete user from db.
   *
   * @param userId to delete
   * @return result value
   * @throws SQLException when SQL error
   */
  public int deleteUser(int userId) {
    int result = 0;
    connection = DaoConnection.getConnection();
    try {
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSUD001));
      preStatement.setInt(ApplicationConstant.NUMBER1, userId);
      result = preStatement.executeUpdate();
      // patient = getPatientDetailsFromResultSet(resultSet);

    } catch (SQLException e) {
      LOGGER.debug(e.toString());
    }
    return result;

  }

}
