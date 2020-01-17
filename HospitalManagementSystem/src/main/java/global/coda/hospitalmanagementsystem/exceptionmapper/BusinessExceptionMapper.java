package global.coda.hospitalmanagementsystem.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.json.JSONObject;

import global.coda.hospitalmanagementsystem.exception.BusinessException;

/**
 * class for Business Exception.
 *
 * @author Chandraleka
 *
 */
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
  /**
   * method to response to the client about the business exception.
   *
   */
  @Override
  public Response toResponse(BusinessException exception) {
    //FIX : ADD ERROR LOG HERE
    // final JsonArray jsonArray = new JsonArray();
    JSONObject jsonPatient = new JSONObject();
    // jsonPatient.put("Status Code", "400");
    //FIX : USE EXCEPTION MESSAGE
    jsonPatient.put("Message", exception.toString());
    // jsonArray.add((JsonValue) jsonPatient);
    return Response.status(400).entity(jsonPatient.toString()).build();

  }

}
