package global.coda.hospitalmanagementsystem.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

/**
 * class for Invalid User id.
 *
 * @author Chandraleka
 *
 */
@Provider
public class InvalidUserIdException extends Exception
implements ExceptionMapper<InvalidUserIdException> {

  public InvalidUserIdException() {
    super();
  }

  public InvalidUserIdException(String message) {
    super(message);
  }

  public InvalidUserIdException(Throwable cause) {
    super(cause);
  }

  @Override
  public Response toResponse(InvalidUserIdException exception) {
    final JsonArray jsonArray = new JsonArray();
    JSONObject jsonPatient = new JSONObject();
    jsonPatient.put("Status Code", "400");
    jsonPatient.put("Message", "Invalid User id entered!\n Please enter a valid user id!");
    jsonArray.add((JsonValue) jsonPatient);
    return Response.status(400).entity(jsonArray.toString()).build();
  }

}
