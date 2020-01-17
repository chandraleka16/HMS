package global.coda.hospitalmanagementsystem.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.dao.DoctorDao;
import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.exception.SystemException;
import global.coda.hospitalmanagementsystem.model.Doctor;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class to for Doctor helper.
 *
 * @author Chandraleka
 *
 */
public class DoctorHelper {
  private Doctor doctor = new Doctor();
  private DoctorDao doctorDao = new DoctorDao();
  private UserHelper userHelper = new UserHelper();

  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorHelper.class);
  private int doctorId = 0;

  /**
   * method to insert a new Doctor details.
   *
   * @param doctorData to insert
   *
   * @return Doctor id
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQl error
   * @throws BusinessException
   *
   *
   */
  public int createDoctor(Doctor doctorData) throws SQLException, BusinessException {
    int userId = userHelper.createUser(doctorData);
    if (userId != 0) {
      try {
        doctorId = doctorDao.createDoctor(doctorData, userId);
        if (doctorId != 0) {
          return doctorId;
        }
      } catch (SQLException e) {
        LOGGER.debug(e.toString());
      } catch (Exception e) {
        LOGGER.debug(e.toString());
      }

    }
    return 0;

  }

  /**
   * method to read doctor details.
   *
   * @param userId to get doctor details
   * @return doctor details
   * @throws InvalidUserIdException when user id is wrong.
   */
  public Doctor readDoctor(int userId) throws InvalidUserIdException {
    doctor = doctorDao.readDoctor(userId);
    if (doctor != null) {
      return doctor;
    } else {
      return null;
    }

  }

  /**
   * method to update doctor details.
   *
   * @param doctorData to update
   * @return result value
   * @throws SQLException
   * @throws BusinessException
   */
  public int updateDoctor(Doctor doctorData, int userId)
      throws BusinessException, SQLException {
    int isUpdateUser = 0;
    int isUpdateDoctor = 0;
    doctorData.setUserId(userId);
    isUpdateUser = userHelper.updateUser(doctorData);
    if (isUpdateUser == 1) {
      isUpdateDoctor = doctorDao.updateDoctor(doctorData);
      if (isUpdateDoctor != 0) {
        return isUpdateDoctor;
      }
    }
    return isUpdateDoctor;

  }

  /**
   * method to delete patient.
   *
   * @param userId to delete
   * @return result value
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException           when SQl error
   * @throws BusinessException
   *
   */
  public int deleteDoctor(int userId) throws SQLException, BusinessException {
    int isDeleteUser = 0;
    int isDeleteDoctor = 0;
    isDeleteUser = userHelper.deleteUser(userId);
    if (isDeleteUser == 1) {
      isDeleteDoctor = doctorDao.deleteDoctor(userId);
    }
    return isDeleteDoctor;

  }

  public List<Patient> listAllPatients(int doctorId)
      throws SystemException, BusinessException {
    List<Patient> patientList = new ArrayList<Patient>();
    try {
      patientList = doctorDao.listAllPatientsUnderDoctor(doctorId);
      if (patientList != null && !patientList.isEmpty()) {
        System.out.println("helper  " + patientList);
        return patientList;
      } else {
        throw new SystemException();
      }
    } catch (Exception e) {
      throw new SystemException(e);
    }

  }

  /**
   * method to list all patients under all doctors.
   *
   * @return doctor map object
   * @throws SystemException when something is wrong
   */
  public Map<Integer, Doctor> listAllPatientsUnderAllDoctors() throws SystemException {
    Map<Integer, Doctor> doctorMap = doctorDao.readAllPatientsUnderAllDoctors();
    if (doctorMap != null && !doctorMap.isEmpty()) {
      return doctorMap;
    } else {
      throw new SystemException();
    }

  }

}