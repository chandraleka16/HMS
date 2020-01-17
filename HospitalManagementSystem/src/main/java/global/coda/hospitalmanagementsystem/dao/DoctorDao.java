package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constant.ApplicationConstant;
import global.coda.hospitalmanagementsystem.constant.ApplicationDaoConstant;
import global.coda.hospitalmanagementsystem.model.Doctor;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class to do doctor dao operations.
 *
 * @author Chandraleka
 *
 */
public class DoctorDao {

  private Doctor doctor = new Doctor();
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientDao.class);
  private static final ResourceBundle DAO_MESSAGE_BUNDLE = ResourceBundle
      .getBundle(ApplicationDaoConstant.DAO_MESSAGES);
  private Connection connection = null;
  private PreparedStatement preStatement = null;
  private ResultSet resultSet = null;
  private int doctorId = 0;

  /**
   * method to insert a new doctor into db.
   *
   * @param doctorData to insert
   * @param userId     to refer the doctor
   * @return doctor id
   * @throws SQLException when SQl error
   */
  public int createDoctor(Doctor doctorData, int userId) throws SQLException {
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection.prepareStatement(
          DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSDI001),
          PreparedStatement.RETURN_GENERATED_KEYS);
      preStatement.setInt(ApplicationConstant.NUMBER1, userId);
      preStatement.setString(ApplicationConstant.NUMBER2,
          doctorData.getDoctorSpicialization());
      preStatement.setInt(ApplicationConstant.NUMBER3, doctorData.getDoctorExperience());
      preStatement.executeUpdate();
      resultSet = preStatement.getGeneratedKeys();
      if (resultSet != null && resultSet.next()) {
        doctorId = resultSet.getInt(ApplicationConstant.NUMBER1);
        connection.commit();
      }
    } catch (SQLException e) {
      connection.rollback();
      LOGGER.info(e.getMessage());
      throw new SQLException(e);

    }
    return doctorId;

  }

  /**
   * method to read doctor details from db.
   *
   * @param userId to get doctor details
   * @return doctor details
   *
   */
  public Doctor readDoctor(int userId) {
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSDR001));
      preStatement.setInt(ApplicationConstant.NUMBER1, doctorId);
      resultSet = preStatement.executeQuery();
      doctor = getPatientDetailsFromResultSet(resultSet);
    } catch (SQLException exception) {
      LOGGER.debug(exception.toString());
    }
    return doctor;

  }

  /**
   * method to get patient details from result set.
   *
   * @param resultSet to get patient details
   * @return patient details
   *
   */
  public Doctor getPatientDetailsFromResultSet(ResultSet resultSet) throws SQLException {
    Doctor doctor = new Doctor();
    if (resultSet.next()) {
      doctor.setUserId(resultSet.getInt("pk_user_id"));
      doctor.setUserName(resultSet.getString("user_name"));
      doctor.setUserPassword(resultSet.getString("user_password"));
      doctor.setUserAge(resultSet.getInt("user_age"));
      doctor.setUserGender(resultSet.getString("user_gender"));
      doctor.setUserMobileNumber(resultSet.getString("user_mobile_number"));
      doctor.setUserEmailId(resultSet.getString("user_email_id"));
      doctor.setUserAddressLine1(resultSet.getString("user_address_line1"));
      doctor.setUserAddressLine2(resultSet.getString("user_address_line2"));
      doctor.setUserAddressLine3(resultSet.getString("user_address_line3"));
      doctor.setUserRoleId(resultSet.getInt("fk_role_id"));
      doctor.setDoctorSpicialization(resultSet.getString("doctor_specialization"));
      doctor.setDoctorExperience(resultSet.getInt("doctor_experience"));
      return doctor;
    } else {
      return null;
    }
  }

  /**
   * method to update doctor details in db.
   *
   * @param doctorData to update
   * @return result value
   */
  public int updateDoctor(Doctor doctorData) {
    try {
      connection = DaoConnection.getConnection();

      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSDU001));
      preStatement.setString(ApplicationConstant.NUMBER1,
          doctorData.getDoctorSpicialization());
      preStatement.setInt(ApplicationConstant.NUMBER2, doctorData.getDoctorExperience());
      preStatement.setInt(ApplicationConstant.NUMBER3, doctorData.getUserId());
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
   * method to delete doctor from db.
   *
   * @param userId to delete
   * @return result value
   *
   */
  public int deleteDoctor(int userId) {
    int result = 0;
    connection = DaoConnection.getConnection();

    try {
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSDD001));
      preStatement.setInt(ApplicationConstant.NUMBER1, doctorId);
      result = preStatement.executeUpdate();
      // patient = getPatientDetailsFromResultSet(resultSet);
    } catch (SQLException e) {
      LOGGER.debug(e.toString());
    }

    return result;

  }

  /**
   * method to list all patient id under a doctor Id.
   *
   * @param doctorId to list patients
   * @return patient id
   * @throws SQLException
   */
  public List<Patient> listAllPatientsUnderDoctor(int doctorId) throws SQLException {
    List<Patient> patientList = null;
    int patientId = 0;
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPUD001));
      preStatement.setInt(ApplicationConstant.NUMBER1, doctorId);
      resultSet = preStatement.executeQuery();
      System.out.println(resultSet.toString());
      patientList = getPatientListFromResultSet(resultSet);
      System.out.println(patientList.toString());
      // while (resultSet.next()) {
      // if (resultSet.next()) {
      // patientId = resultSet.getInt("fk_patient_id");
      // }
      // }
    } catch (SQLException e) {
      throw new SQLException(e);
      // LOGGER.info(e.getMessage());

    }
    return patientList;
  }

  public List<Patient> getPatientListFromResultSet(ResultSet resultSet) throws SQLException {
    List<Patient> patientList = new ArrayList<>();
    try {
      connection = DaoConnection.getConnection();
      while (resultSet.next()) {
        Patient tempPatient = new Patient();
        System.out.println(resultSet.getInt("pk_user_id"));
        System.out.println(resultSet.getString("user_name"));
        tempPatient.setUserId(resultSet.getInt("pk_user_id"));
        tempPatient.setUserName(resultSet.getString("user_name"));
        tempPatient.setUserPassword(resultSet.getString("user_password"));
        tempPatient.setUserAge(resultSet.getInt("user_age"));
        tempPatient.setUserGender(resultSet.getString("user_gender"));
        tempPatient.setUserMobileNumber(resultSet.getString("user_mobile_number"));
        tempPatient.setUserEmailId(resultSet.getString("user_email_id"));
        tempPatient.setUserAddressLine1(resultSet.getString("user_address_line1"));
        tempPatient.setUserAddressLine2(resultSet.getString("user_address_line2"));
        tempPatient.setUserAddressLine3(resultSet.getString("user_address_line3"));
        tempPatient.setUserRoleId(resultSet.getInt("fk_role_id"));
        tempPatient.setPatientDisease(resultSet.getString("patient_disease"));
        // doctor.setDoctorExperience(resultSet.getInt("doctor_experience"));
        patientList.add(tempPatient);

      }

    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      throw new SQLException();
    }
    return patientList;
  }

  public Map<Integer, Doctor> readAllPatientsUnderAllDoctors() {
    Map<Integer, Doctor> doctorMap = new HashMap<Integer, Doctor>();
    int patientId = 0;
    try {
      connection = DaoConnection.getConnection();
      preStatement = connection
          .prepareStatement(DAO_MESSAGE_BUNDLE.getString(ApplicationDaoConstant.HMSPUD002));
      resultSet = preStatement.executeQuery();
      System.out.println(resultSet.toString());
      doctorMap = getDoctorListFromResultSet(resultSet);
    } catch (SQLException e) {
      LOGGER.debug(e.getMessage());
    }
    return doctorMap;
  }

  private Map<Integer, Doctor> getDoctorListFromResultSet(ResultSet resultSet)
      throws SQLException {
    Map<Integer, Doctor> doctorMap = new HashMap<Integer, Doctor>();
    try {
      connection = DaoConnection.getConnection();

      while (resultSet != null && resultSet.next()) {
        List<Patient> patientList = new ArrayList<Patient>();
        Patient tempPatient = new Patient();
        Doctor tempDoctor = new Doctor();
        tempPatient.setUserId(resultSet.getInt("pk_user_id"));
        tempPatient.setUserName(resultSet.getString("user_name"));
        tempPatient.setUserPassword(resultSet.getString("user_password"));
        tempPatient.setUserAge(resultSet.getInt("user_age"));
        tempPatient.setUserGender(resultSet.getString("user_gender"));
        tempPatient.setUserMobileNumber(resultSet.getString("user_mobile_number"));
        tempPatient.setUserEmailId(resultSet.getString("user_email_id"));
        tempPatient.setUserAddressLine1(resultSet.getString("user_address_line1"));
        tempPatient.setUserAddressLine2(resultSet.getString("user_address_line2"));
        tempPatient.setUserAddressLine3(resultSet.getString("user_address_line3"));
        tempPatient.setPatientDisease(resultSet.getString("patient_disease"));
        tempDoctor.setUserId(resultSet.getInt("fk_doctor_id"));
        patientList.add(tempPatient);
        tempDoctor.setPatientList(patientList);
        doctorMap.put(tempDoctor.getUserId(), tempDoctor);
      }
    } catch (SQLException e) {
      LOGGER.info(e.getMessage());
      throw new SQLException(e);
    }
    return doctorMap;
  }
}
