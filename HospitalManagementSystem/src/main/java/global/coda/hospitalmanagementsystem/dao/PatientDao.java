package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constant.ApplicationDaoConstant;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class to do patient dao operations.
 *
 * @author Chandraleka
 *
 */
public class PatientDao {

  private Patient patient = new Patient();
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientDao.class);
  private static final ResourceBundle DAO_MESSAGE_BUNDLE = ResourceBundle
      .getBundle(ApplicationDaoConstant.DAO_MESSAGES);
  private Connection connection = null;
  private PreparedStatement preStatement = null;
  private ResultSet resultSet = null;
  private int patientId = 0;

  /**
   * method to insert a new patient into db.
   *
   * @param patientData to insert
   * @param userId      to refer the patient
   * @return patient id
   * @throws SQLException when SQL query is wrong.
   */
  public int createPatient(Patient patientData, int userId) throws SQLException {

    try {
      connection = DaoConnection.getConnection();
      preStatement = connection.prepareStatement(
          DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPI001),
          PreparedStatement.RETURN_GENERATED_KEYS);
      preStatement.setInt(1, userId);
      preStatement.setString(2, patientData.getPatientDisease());
      preStatement.executeUpdate();
      resultSet = preStatement.getGeneratedKeys();
      if (resultSet.next()) {
        patientId = resultSet.getInt(1);
        connection.commit();
      }
    } catch (SQLException e) {
      connection.rollback();
      LOGGER.error(e.getMessage());

    }
    return patientId;

  }

  /**
   * method to read patient details from db.
   *
   * @param userId to get patient details
   * @return patient details
   * @throws SQLException when SQL query is wrong
   *
   */
  public Patient readPatient(int userId) throws SQLException, InvalidUserIdException {
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPR001));
      preStatement.setInt(1, patientId);
      resultSet = preStatement.executeQuery();
      patient = getPatientDetailsFromResultSet(resultSet);
    } catch (SQLException exception) {
      //FIX : THROW CUSTOM EXCEPTION AND CATCH IT IN HELPER. THROW SYSTEM OR BUSINESS EXCEPTION IN HELPER
      LOGGER.debug(exception.toString());
    }
    return patient;

  }

  /**
   * method to get patient details from result set.
   *
   * @param resultSet to get patient details
   * @return patient details
   * @throws SQLException when SQL query is wrong.
   *
   */
  public Patient getPatientDetailsFromResultSet(ResultSet resultSet) {
    Patient patient = new Patient();
    try {
      if (resultSet.next()) {
        patient.setUserId(resultSet.getInt("pk_user_id"));
        patient.setUserName(resultSet.getString("user_name"));
        patient.setUserPassword(resultSet.getString("user_password"));
        patient.setUserAge(resultSet.getInt("user_age"));
        patient.setUserGender(resultSet.getString("user_gender"));
        patient.setUserMobileNumber(resultSet.getString("user_mobile_number"));
        patient.setUserEmailId(resultSet.getString("user_email_id"));
        patient.setUserAddressLine1(resultSet.getString("user_address_line1"));
        patient.setUserAddressLine2(resultSet.getString("user_address_line2"));
        patient.setUserAddressLine3(resultSet.getString("user_address_line3"));
        patient.setUserRoleId(resultSet.getInt("fk_role_id"));
        // patient.setPatientDisease(resultSet.getString("patient_disease"));

      } else {
        return null;
      }
    } catch (SQLException e) {
      LOGGER.debug(e.toString());
    }
    return patient;
  }

  /**
   * method to update patient details in db.
   *
   * @param patientData to update
   * @return result value
   */
  public int updatePatient(Patient patientData) {
    try {
      connection = DaoConnection.getConnection();

      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPU001));
      preStatement.setString(1, patientData.getPatientDisease());
      preStatement.setInt(2, patientData.getUserId());
      int result = preStatement.executeUpdate();
      LOGGER.debug(String.valueOf(result));
      if (result == 1) {
        connection.commit();
        return 1;
      } else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.debug(e.getMessage());
      return 0;
    }

  }

  /**
   * method to delete patient from db.
   *
   * @param userId to delete
   * @return result value
   */
  public int deletePatient(int userId) {
    int result = 0;
    connection = DaoConnection.getConnection();

    try {
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPD001));
      preStatement.setInt(1, patientId);
      result = preStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.debug(e.toString());
    }

    // patient = getPatientDetailsFromResultSet(resultSet);
    return result;

  }

}
