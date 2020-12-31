package com.huebnerj.financial.freedom;

import com.huebnerj.financial.freedom.rest.SimpleCalculationResource;
import com.huebnerj.financial.freedom.service.SimpleCalculationService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class SimpleCalculationApp extends Application<SimpleCalculationConfiguration> {

  public static void main(String[] args) throws Exception {
    new SimpleCalculationApp().run(args);
  }

  @Override
  public void run(SimpleCalculationConfiguration configuration, Environment environment) {

    var simpleCalculationService = new SimpleCalculationService(configuration);
    var simpleCalculationEndpoint = new SimpleCalculationResource(simpleCalculationService);

    environment.jersey().register(simpleCalculationEndpoint);
  }
}
