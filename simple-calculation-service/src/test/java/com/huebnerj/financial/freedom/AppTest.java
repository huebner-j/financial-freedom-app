package com.huebnerj.financial.freedom;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
class AppTest {

  public static final DropwizardAppExtension<SimpleCalculationConfiguration> DROPWIZARD =
      new DropwizardAppExtension<>(
          SimpleCalculationApp.class, new SimpleCalculationConfiguration());

  @Test
  void applicationShouldStartProperly() {}
}
