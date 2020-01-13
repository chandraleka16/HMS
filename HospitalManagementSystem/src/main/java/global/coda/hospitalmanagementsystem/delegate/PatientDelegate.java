package global.coda.hospitalmanagementsystem.delegate;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.helper.PatientHelper;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class for patient deligate.
 *
 * @author Chandraleka
 *
 */
public class PatientDelegate {
  private Patient patient = new Patient();
  private PatientHelper patientHelper = new PatientHelper();

  private static final Logger LOGGER = LoggerFactory.getLogger(PatientDelegate.class);
  private int patientId = 0;

  /**
   * method to create a new patient data.
   *
   * @param patientData to insert
   *
   * @return patient id
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQL query is wrong.
   * @throws BusinessException
   */
  public int createPatient(Patient patientData) throws SQLException, BusinessException {
    patientId = patientHelper.createPatient(patientData);
    return patientId;

  }

  /**
   * method to read patient details from db.
   *
   * @param userId to get patient details
   * @return patient details
   * @throws InvalidUserIdException when user id is wrong.
   */
  public Patient readPatient(int userId) throws InvalidUserIdException {
    try {
      patient = patientHelper.readPatient(userId);

    } catch (InvalidUserIdException e) {
      throw new InvalidUserIdException();
    }
    return patient;

  }

  /**
   * method to update patient details in db.
   *
   * @param patientData to update
   * @return result value
   * @throws SQLException
   * @throws BusinessException
   */
  public int updatePatient(Patient patientData, int userId)
      throws BusinessException, SQLException {
    return patientHelper.updatePatient(patientData, userId);

  }

  /**
   * method to delete patient from db.
   *
   * @param userId to delete
   * @return result value
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQL error
   * @throws BusinessException
   */
  public int deletePatient(int userId)
      throws SQLException, InvalidUserIdException, BusinessException {
    return patientHelper.deletePatient(userId);

  }
}