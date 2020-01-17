package global.coda.hospitalmanagementsystem.api;

import java.sql.SQLException;

import org.junit.Test;

import global.coda.hospitalmanagementsystem.delegate.DoctorDelegate;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.model.Doctor;

public class DoctorApiTest {
  private DoctorDelegate doctorDeligate = new DoctorDelegate();
  private Doctor doctor = new Doctor();

  @Test
  public void deleteDoctorDetails() throws InvalidUserIdException, SQLException {
    // int doctorId = DoctorTestConstant.USER_ID;
    // assertEquals(1, doctorDeligate.deleteDoctor(doctorId));

  }

}
