package global.coda.hospitalmanagementsystem.model;

import java.util.List;

/**
 * Doctor Bean class.
 *
 * @author CHANDRALEKA
 *
 */
public class Doctor extends User {
  private String doctorSpicialization;
  private int doctorExperience;
  private List<Patient> patientList;

  /**
   * method to get list of patients under doctor.
   * 
   * @return list of patients under doctor
   */
  public List<Patient> getPatientList() {
    return patientList;
  }

  /**
   * method to set list of patients under doctor.
   * 
   * @param patientList to set list of patients under doctor
   */
  public void setPatientList(List<Patient> patientList) {
    this.patientList = patientList;
  }

  /**
   * method to get doctor specialization.
   *
   * @return doctor specialization
   */
  public String getDoctorSpicialization() {
    return doctorSpicialization;
  }

  /**
   * method to set doctor specialization.
   *
   * @param doctorSpicialization to set
   */
  public void setDoctorSpicialization(String doctorSpicialization) {
    this.doctorSpicialization = doctorSpicialization;
  }

  /**
   * method to get doctor experience.
   *
   * @return doctor experience
   */
  public int getDoctorExperience() {
    return doctorExperience;
  }

  /**
   * method to set doctor experience.
   *
   * @param doctorExperience to set
   */
  public void setDoctorExperience(int doctorExperience) {
    this.doctorExperience = doctorExperience;
  }
}
