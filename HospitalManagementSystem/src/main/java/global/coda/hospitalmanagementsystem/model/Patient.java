package global.coda.hospitalmanagementsystem.model;

/**
 * Patient bean class.
 *
 * @author CHANDRALEKA
 *
 */
public class Patient extends User {

  /**method to convert the patient bean to string.
   *
   */
  @Override
  public String toString() {
    return "Patient [patientDisease=" + patientDisease + "]";
  }

  private String patientDisease;

  /**
   * method to get patient disease.
   *
   * @return patient disease
   */
  public String getPatientDisease() {
    return patientDisease;
  }

  /**
   * method to set patient disease.
   *
   * @param patientDisease to set
   */
  public void setPatientDisease(String patientDisease) {
    this.patientDisease = patientDisease;
  }
}
