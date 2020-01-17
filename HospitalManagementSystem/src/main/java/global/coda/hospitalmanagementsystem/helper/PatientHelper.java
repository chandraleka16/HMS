package global.coda.hospitalmanagementsystem.helper;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.dao.PatientDao;
import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class to for patient helper.
 *
 * @author Chandraleka
 *
 */
public class PatientHelper {
  private Patient patient = new Patient();
  private PatientDao patientDao = new PatientDao();
  private UserHelper userHelper = new UserHelper();

  private static final Logger LOGGER = LoggerFactory.getLogger(PatientHelper.class);
  private int patientId = 0;

  /**
   * method to insert a new patient details.
   *
   * @param patientData to insert
   *
   * @return patient id
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException
   * @throws BusinessException
   *
   */
  public int createPatient(Patient patientData) throws SQLException, BusinessException {
    int userId = userHelper.createUser(patientData);
    try {
      patientId = patientDao.createPatient(patientData, userId);
    } catch (SQLException e) {
      LOGGER.debug(e.toString());
    }
    return patientId;

  }

  /**
   * method to read patient details.
   *
   * @param userId to get patient details
   * @return patient details
   * @throws InvalidUserIdException when user id is wrong.
   */
  public Patient readPatient(int userId) throws InvalidUserIdException {
    try {
      patient = patientDao.readPatient(userId);
      //FIX : CATCH GEENRIC EXCEPTION
    } catch (SQLException e) {
      //FIX : USE MESSAGE
      throw new InvalidUserIdException();
    }
    return patient;

  }

  /**
   * method to update patient details.
   *
   * @param patientData to update
   * @return result value
   * @throws SQLException
   * @throws BusinessException
   */
  public int updatePatient(Patient patientData, int userId)
      throws BusinessException, SQLException {
    int isUpdateUser = 0;
    int isUpdatePatient = 0;
    patientData.setUserId(userId);
    isUpdateUser = userHelper.updateUser(patientData);
    if (isUpdateUser == 1) {
      isUpdatePatient = patientDao.updatePatient(patientData);
    }
    return isUpdatePatient;

  }

  /**
   * method to delete patient.
   *
   * @param userId to delete
   * @return result value
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQL error
   * @throws BusinessException
   */
  public int deletePatient(int userId) throws SQLException, BusinessException {
    int isUpdateUser = 0;
    int isUpdatePatient = 0;
    isUpdateUser = userHelper.deleteUser(userId);
    if (isUpdateUser == 1) {
      isUpdatePatient = patientDao.deletePatient(userId);
    }
    return isUpdatePatient;
  }
}