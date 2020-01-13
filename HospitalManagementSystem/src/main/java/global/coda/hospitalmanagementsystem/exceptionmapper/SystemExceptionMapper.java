package global.coda.hospitalmanagementsystem.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import global.coda.hospitalmanagementsystem.exception.SystemException;

/**
 * class to define System exception.
 *
 * @author Chandraleka
 *
 */
public class SystemExceptionMapper implements ExceptionMapper<SystemException> {

  /**
   * method to response to the client about the system exception.
   *
   */
  @Override
  public Response toResponse(SystemException exception) {
    final JsonArray jsonArray = new JsonArray();
    JSONObject jsonPatient = new JSONObject();
    // jsonPatient.put("Status Code", "500");
    jsonPatient.put("Message", "Something went wrong!");
    // jsonArray.add((JsonValue) jsonPatient);
    return Response.status(400).entity(jsonPatient.toString()).build();

  }

}
