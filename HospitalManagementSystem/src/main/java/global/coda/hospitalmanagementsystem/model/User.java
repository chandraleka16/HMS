package global.coda.hospitalmanagementsystem.model;

/**
 * User bean class.
 *
 * @author CHANDRALEKA
 *
 */
public class User {

  private int userId;
  private String userName;
  private String userPassword;
  private int userAge;
  private String userGender;
  private String userMobileNumber;
  private String userEmailId;
  private String userAddressLine1;
  private String userAddressLine2;
  private String userAddressLine3;
  private int userRoleId;

  /**
   * method to get user name.
   *
   * @return user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * method to set user name.
   *
   * @param userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * method to get user password.
   *
   * @return user password
   */
  public String getUserPassword() {
    return userPassword;
  }

  /**
   * method to set user password.
   *
   * @param userPassword to set
   */
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  /**
   * method to get user age.
   *
   * @return user age
   */
  public int getUserAge() {
    return userAge;
  }

  /**
   * method to sat user age.
   *
   * @param userAge to set
   */
  public void setUserAge(int userAge) {
    this.userAge = userAge;
  }

  /**
   * method to get user gender.
   *
   * @return user gender
   */
  public String getUserGender() {
    return userGender;
  }

  /**
   * method to set user gender.
   *
   * @param userGender to set
   */
  public void setUserGender(String userGender) {
    this.userGender = userGender;
  }

  /**
   * method to get user mobile number.
   *
   * @return user mobile number
   */
  public String getUserMobileNumber() {
    return userMobileNumber;
  }

  /**
   * method to set user mobile number.
   *
   * @param userMobileNumber to set
   */
  public void setUserMobileNumber(String userMobileNumber) {
    this.userMobileNumber = userMobileNumber;
  }

  /**
   * method to get user email id.
   *
   * @return user email id
   */
  public String getUserEmailId() {
    return userEmailId;
  }

  /**
   * method to set user email id.
   *
   * @param userEmailId to set
   */
  public void setUserEmailId(String userEmailId) {
    this.userEmailId = userEmailId;
  }

  /**
   * method to get user address line1.
   *
   * @return user address line1
   */
  public String getUserAddressLine1() {
    return userAddressLine1;
  }

  /**
   * method to set user address line1.
   *
   * @param userAddressLine1 to set
   */
  public void setUserAddressLine1(String userAddressLine1) {
    this.userAddressLine1 = userAddressLine1;
  }

  /**
   * method to get user address line2.
   *
   * @return user address line2
   */
  public String getUserAddressLine2() {
    return userAddressLine2;
  }

  /**
   * method to set user address line2.
   *
   * @param userAddressLine2 to set
   */
  public void setUserAddressLine2(String userAddressLine2) {
    this.userAddressLine2 = userAddressLine2;
  }

  /**
   * method to get user address line3.
   *
   * @return user address line3
   */
  public String getUserAddressLine3() {
    return userAddressLine3;
  }

  /**
   * method to set user address line3.
   *
   * @param userAddressLine3 to set
   */
  public void setUserAddressLine3(String userAddressLine3) {
    this.userAddressLine3 = userAddressLine3;
  }

  /**
   * method to get user role id.
   *
   * @return user role id
   */
  public int getUserRoleId() {
    return userRoleId;
  }

  /**
   * method to set user role id.
   *
   * @param userRoleId to set
   */
  public void setUserRoleId(int userRoleId) {
    this.userRoleId = userRoleId;
  }

  /**
   * method to get user id.
   *
   * @return user id
   */
  public int getUserId() {
    return userId;
  }

  /**
   * method to set user id.
   *
   * @param userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * method to convert the user bean to string.
   *
   */
  @Override
  public String toString() {
    return "User [userId=" + userId + ", userName=" + userName + "," + " userPassword="
        + userPassword + ", userAge=" + userAge + ", userGender=" + userGender + ", "
        + "userMobileNumber=" + userMobileNumber + ", " + "userEmailId=" + userEmailId
        + ", userAddressLine1=" + userAddressLine1 + ", userAddressLine2=" + userAddressLine2
        + ", userAddressLine3=" + userAddressLine3 + ", userRoleId=" + userRoleId + "]";
  }

}
