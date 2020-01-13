package global.coda.hospitalmanagementsystem.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.exception.SystemException;
import global.coda.hospitalmanagementsystem.helper.DoctorHelper;
import global.coda.hospitalmanagementsystem.model.Doctor;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class for Doctor delegate.
 *
 * @author Chandraleka
 *
 */
public class DoctorDelegate {
  private Doctor doctor = new Doctor();
  private DoctorHelper doctorHelper = new DoctorHelper();

  private static final Logger LOGGER = LoggerFactory.getLogger(PatientDelegate.class);
  private int doctorId = 0;

  /**
   * method to create a new patient data.
   *
   * @param doctorData to insert
   *
   * @return patient id
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQL query is wrong.
   * @throws BusinessException
   */
  public int createDoctor(Doctor doctorData) throws SQLException, BusinessException {
    doctorId = doctorHelper.createDoctor(doctorData);
    return doctorId;

  }

  /**
   * method to read patient details from db.
   *
   * @param userId to get patient details
   * @return patient details
   * @throws InvalidUserIdException when user id is wrong.
   */
  public Doctor readDoctor(int userId) throws InvalidUserIdException {
    try {
      doctor = doctorHelper.readDoctor(userId);

    } catch (InvalidUserIdException e) {
      throw new InvalidUserIdException();
    }
    return doctor;

  }

  /**
   * method to update Doctor details in db.
   *
   * @param Doctor Data to update
   * @return result value
   * @throws SQLException
   * @throws BusinessException
   */
  public int updateDoctor(Doctor doctorData, int userId)
      throws BusinessException, SQLException {
    return doctorHelper.updateDoctor(doctorData, userId);

  }

  /**
   * method to delete Doctor from db.
   *
   * @param userId to delete
   * @return result value
   *
   * @throws SQLException
   * @throws BusinessException
   */
  public int deleteDoctor(int userId) throws SQLException, BusinessException {
    return doctorHelper.deleteDoctor(userId);

  }

  public List<Patient> listAllPatients(int doctorId)
      throws SystemException, BusinessException {
    try {
      List<Patient> patientList = new ArrayList<Patient>();
      patientList = doctorHelper.listAllPatients(doctorId);
      System.out.println(patientList);
      return patientList;
    } catch (Exception e) {
      throw new SystemException();
    }

  }

  public List<Patient> listAllPatientsUnderDoctors() throws SystemException {
    try {
      List<Patient> patientList = new ArrayList<Patient>();
      patientList = doctorHelper.listAllPatientsUnderAllDoctors();
      return patientList;
    } catch (Exception e) {
      throw new SystemException();
    }

  }

}
