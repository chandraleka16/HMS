package global.coda.hospitalmanagementsystem.helper;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.dao.UserDao;
import global.coda.hospitalmanagementsystem.delegate.PatientDelegate;
import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.model.User;

/**
 * class for user helper.
 *
 * @author Chandraleka
 *
 */
public class UserHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientDelegate.class);

  private UserDao userDao = new UserDao();
  private int userId = 0;

  /**
   * method to insert a new user details.
   *
   * @param userData to insert
   * @return user id
   * @throws BusinessException
   * @throws SQLException
   *
   */

  public int createUser(User userData) throws BusinessException, SQLException {
    int userId = userDao.createUser(userData);
    if (userId != 0) {
      return userId;
    } else {
      throw new BusinessException();
    }

  }

  /**
   * method to update user details in db.
   *
   * @param userData to update
   * @return result value
   */
  public int updateUser(User userData) throws BusinessException, SQLException {
    return userDao.updateUser(userData);

  }

  /**
   * method to delete user from db.
   *
   * @param userId to delete
   * @return result value
   * @throws SQLException when SQL error
   */
  public int deleteUser(int userId) throws SQLException, BusinessException {
    return userDao.deleteUser(userId);

  }

}
