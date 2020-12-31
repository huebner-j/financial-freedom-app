package com.huebnerj.financial.freedom.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.huebnerj.financial.freedom.SimpleCalculationApp;
import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;
import com.huebnerj.financial.freedom.rest.model.FinancialFreedomCalculation;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
class SimpleCalculationResourceIT {

  public static final DropwizardAppExtension<SimpleCalculationConfiguration> DROPWIZARD =
      new DropwizardAppExtension<>(
          SimpleCalculationApp.class, new SimpleCalculationConfiguration());

  @Test
  void shouldCalculateResultProperly() {

    Client client = DROPWIZARD.client();
    try (Response response =
        client
            .target(String.format("http://localhost:%s/calculations", DROPWIZARD.getLocalPort()))
            .queryParam("seedCapital", 10000)
            .queryParam("interest", 7.0)
            .queryParam("monthlySavingRate", 1000)
            .queryParam("desiredMonthlyIncome", 5000)
            .request(MediaType.APPLICATION_JSON)
            .get()) {

      var calculationResult = response.readEntity(FinancialFreedomCalculation.class);

      assertThat(calculationResult.getYearsNeeded()).isEqualTo(26);
      assertThat(calculationResult.getSavedAmount().doubleValue()).isEqualTo(312000.0);
      assertThat(calculationResult.getInterestAmount().doubleValue()).isEqualTo(560191.17);
      assertThat(calculationResult.getTotalAmount().doubleValue()).isEqualTo(882191.17);
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }
  }

  @Test
  void shouldNeverReachEarlyRetirement() {

    Client client = DROPWIZARD.client();
    try (Response response =
        client
            .target(String.format("http://localhost:%s/calculations", DROPWIZARD.getLocalPort()))
            .queryParam("seedCapital", 10000)
            .queryParam("interest", 0.1)
            .queryParam("monthlySavingRate", 1000)
            .queryParam("desiredMonthlyIncome", 10000)
            .request(MediaType.APPLICATION_JSON)
            .get()) {

      var calculationResult = response.readEntity(FinancialFreedomCalculation.class);

      assertThat(calculationResult.getYearsNeeded()).isNull();
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }
  }

  @Test
  void shouldReject() {

    Client client = DROPWIZARD.client();
    try (Response response =
        client
            .target(String.format("http://localhost:%s/calculations", DROPWIZARD.getLocalPort()))
            .queryParam("seedCapital", 10000)
            .queryParam("monthlySavingRate", 1000)
            .queryParam("desiredMonthlyIncome", 5000)
            .request(MediaType.APPLICATION_JSON)
            .get()) {

      assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST_400);
    }
  }
}
