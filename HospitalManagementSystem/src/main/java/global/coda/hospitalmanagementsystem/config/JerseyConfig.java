package global.coda.hospitalmanagementsystem.config;

import org.glassfish.jersey.server.ResourceConfig;

import global.coda.hospitalmanagementsystem.api.DoctorApi;
import global.coda.hospitalmanagementsystem.api.PatientApi;
import global.coda.hospitalmanagementsystem.exceptionmapper.BusinessExceptionMapper;
import global.coda.hospitalmanagementsystem.exceptionmapper.SystemExceptionMapper;

/**
 * Jersey configuration class.
 *
 * @author Chandraleka
 *
 */
public class JerseyConfig extends ResourceConfig {

  /**
   * Constructor to configure the jersey.
   *
   */
  public JerseyConfig() {
    register(PatientApi.class);
    register(DoctorApi.class);
    register(SystemExceptionMapper.class);
    register(BusinessExceptionMapper.class);
  }
}
