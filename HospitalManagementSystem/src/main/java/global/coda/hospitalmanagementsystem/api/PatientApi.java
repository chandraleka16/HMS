package global.coda.hospitalmanagementsystem.api;

import java.sql.SQLException;

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

import global.coda.hospitalmanagementsystem.delegate.PatientDelegate;
import global.coda.hospitalmanagementsystem.exception.BusinessException;
import global.coda.hospitalmanagementsystem.exception.InvalidUserIdException;
import global.coda.hospitalmanagementsystem.exceptionmapper.SystemExceptionMapper;
import global.coda.hospitalmanagementsystem.model.Patient;

/**
 * class for Patient API.
 *
 * @author Chandraleka
 *
 */
@Path("/service/patients")
public class PatientApi {
  private PatientDelegate patientDeligate = new PatientDelegate();
  private Patient patient = new Patient();

  /**
   * Method to create a patient details into db.
   *
   * @param patientData to insert
   * @return Response bto the client
   */
  @POST
  @Path("/createPatient")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  // FIX : THROW SYSTEM EXCEPTION
  public Response createPatientDetails(Patient patientData)
      throws BusinessException, SQLException {

    int patientId = patientDeligate.createPatient(patientData);

    return null;
  }

  /**
   * method to read patient details using patient id.
   *
   * @param patientId to read patient details
   * @return response to the client
   */
  @GET
  @Path("/readPatient/{patientId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response readpatientDetails(@PathParam("patientId") int patientId)
      throws InvalidUserIdException {
    // FIX : USE TRACE ENTRY AND TRACE EXIT LOGGERS
    JsonArray jsonArray = new JsonArray();
    JSONObject jsonPatient = new JSONObject();
    // FIX : USE STATIC
    patient = patientDeligate.readPatient(patientId);
    // jsonPatient.put("Status Code", "200");
    jsonPatient.put("Message", patient);
    // jsonArray.put(jsonPatient);
    // FIX : USE BEAN INSTEAD OF STRING
    return Response.status(200).entity(jsonPatient.toString(2)).build();

  }

  /**
   * method to update patient details.
   *
   * @param userId to update
   * @param user   to update
   * @return response the client
   * @throws SystemExceptionMapper when SQL query error
   */
  @PUT
  @Path("updatepatient/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updatePatientDetails(@PathParam("userId") int userId, Patient patient)
      throws BusinessException, SQLException {

    int isUpdatePatient = patientDeligate.updatePatient(patient, userId);

    return Response.status(Response.Status.OK).entity("Updated").build();

  }

  /**
   * Method to delete patient details.
   *
   * @param patientId to delete
   * @return response to the client
   * @throws InvalidUserIdException
   * @throws BusinessException
   * @throws SQLException
   */
  @GET
  @Path("/deletePatient/{patientId}")
  public Response deletePatientDetails(@PathParam("patientId") int patientId)
      throws InvalidUserIdException, SQLException, BusinessException {
    JsonArray jsonArray = new JsonArray();
    JSONObject jsonPatient = new JSONObject();

    int isDeletePatient = patientDeligate.deletePatient(patientId);

    // jsonPatient.put("Status Code", "200");
    jsonPatient.put("Message", patient);
    // jsonArray.put(jsonPatient);

    return Response.status(200).entity(jsonPatient.toString(2)).build();

  }

}
