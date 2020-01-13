package global.coda.hospitalmanagementsystem.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import global.coda.hospitalmanagementsystem.delegate.DoctorDelegate;
import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.exception.SystemException;
import global.coda.hospitalmanagementsystem.exceptionmapper.SystemExceptionMapper;
import global.coda.hospitalmanagementsystem.model.Doctor;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class for Doctor API.
 *
 * @author Chandraleka
 *
 */
@Path("/service/doctors")
public class DoctorApi {
  private DoctorDelegate doctorDeligate = new DoctorDelegate();
  private Doctor doctor = new Doctor();

  /**
   * Method to create a Doctor details into db.
   *
   * @param doctorData to insert
   * @return response to the client
   * @throws InvalidUserIdException when user id is wrong
   * @throws SQLException
   * @throws BusinessException
   */
  @POST
  @Path("/createDoctor")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createPatientDetails(Doctor doctorData)
      throws InvalidUserIdException, SQLException, BusinessException {
    int doctorId = doctorDeligate.createDoctor(doctorData);
    return null;
  }

  /**
   * method to read Doctor details using doctor id.
   *
   * @param doctorId to read patient details
   * @return response to the client
   */
  @GET
  @Path("/readDoctor/{doctorId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response readDoctorDetails(@PathParam("doctorId") int doctorId)
      throws InvalidUserIdException {
    JsonArray jsonArray = new JsonArray();
    JSONObject jsonDoctor = new JSONObject();
    doctor = doctorDeligate.readDoctor(doctorId);
    // jsonPatient.put("Status Code", "200");
    jsonDoctor.put("Message", doctor);
    // jsonArray.put(jsonPatient);
    return Response.status(200).entity(jsonDoctor.toString()).build();

  }

  /**
   * method to update doctor details.
   *
   * @param userId to update
   * @param user   to update
   * @return response the client
   * @throws SQLException
   * @throws SystemExceptionMapper when SQL query error
   */
  @PUT
  @Path("updateDoctor/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateDoctorDetails(@PathParam("userId") int userId, Doctor doctor)
      throws BusinessException, InvalidUserIdException, SQLException {

    doctorDeligate.updateDoctor(doctor, userId);
    if (userId != 0) {
      return Response.status(Response.Status.OK).entity("Updated").build();
    } else {
      throw new InvalidUserIdException();
    }
  }

  /**
   * Method to delete doctor details.
   *
   * @param doctorId to delete
   * @return response to the client
   * @throws InvalidUserIdException
   * @throws SQLException
   * @throws BusinessException
   */
  @GET
  @Path("/deleteDoctor/{doctorId}")
  public Response deleteDoctorDetails(@PathParam("doctorId") int doctorId)
      throws InvalidUserIdException, SQLException, BusinessException {
    JsonArray jsonArray = new JsonArray();
    JSONObject jsonDoctor = new JSONObject();
    int isDeleteDoctor = doctorDeligate.deleteDoctor(doctorId);
    // jsonPatient.put("Status Code", "200");
    jsonDoctor.put("Message", doctor);
    // jsonArray.put(jsonPatient);

    return Response.status(200).entity(jsonDoctor.toString()).build();

  }

  @GET
  @Path("/listAllPatients/{doctorId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response listAllPatientsUnderDoctorId(@PathParam("doctorId") int doctorId)
      throws BusinessException, SystemException {
    List<Patient> patientList = doctorDeligate.listAllPatients(doctorId);
    return Response.status(200).entity(patientList.toString()).build();

  }

  @GET
  @Path("/listAllPatients")
  @Produces(MediaType.APPLICATION_JSON)
  public Response listAllPatientsUnderDoctors() throws BusinessException, SystemException {
    List<Patient> patientList = doctorDeligate.listAllPatientsUnderDoctors();
    return Response.status(200).entity(patientList).build();

  }

}
