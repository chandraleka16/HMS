package global.coda.hospitalmanagementsystem.model;

/**
 * Doctor Bean class.
 *
 * @author CHANDRALEKA
 *
 */
public class Doctor extends User {
  private String doctorSpicialization;
  private int doctorExperience;

  /** method to get doctor specialization.
   *
   * @return doctor specialization
   */
  public String getDoctorSpicialization() {
    return doctorSpicialization;
  }

  /**method to set doctor specialization.
   *
   * @param doctorSpicialization to set
   */
  public void setDoctorSpicialization(String doctorSpicialization) {
    this.doctorSpicialization = doctorSpicialization;
  }

  /**method to get doctor experience.
   *
   * @return doctor experience
   */
  public int getDoctorExperience() {
    return doctorExperience;
  }

  /**method to set doctor experience.
   *
   * @param doctorExperience to set
   */
  public void setDoctorExperience(int doctorExperience) {
    this.doctorExperience = doctorExperience;
  }
}
