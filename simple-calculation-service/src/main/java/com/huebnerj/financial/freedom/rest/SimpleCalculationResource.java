package com.huebnerj.financial.freedom.rest;

import com.huebnerj.financial.freedom.rest.model.FinancialFreedomCalculation;
import com.huebnerj.financial.freedom.service.SimpleCalculationService;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("calculations")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleCalculationResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCalculationResource.class);

  private final SimpleCalculationService simpleCalculationService;

  public SimpleCalculationResource(SimpleCalculationService simpleCalculationService) {
    this.simpleCalculationService = simpleCalculationService;
  }

  @GET
  public FinancialFreedomCalculation calculate(
      @QueryParam("seedCapital") @NotNull Long seedCapital,
      @QueryParam("interest") @NotNull @DecimalMin("0.0") Double interest,
      @QueryParam("monthlySavingRate") @NotNull @Min(0) Integer monthlySavingRate,
      @QueryParam("desiredMonthlyIncome") @NotNull @Min(0) Integer desiredMonthlyIncome) {

    LOGGER.debug(
        "Start calculating financial freedom for params: seedCapital {}, interest {}, monthlySavingRate {}, desiredMonthlyIncome {}",
        seedCapital,
        interest,
        monthlySavingRate,
        desiredMonthlyIncome);

    return simpleCalculationService
        .calculate(seedCapital, interest, monthlySavingRate, desiredMonthlyIncome)
        .orElse(new FinancialFreedomCalculation());
  }
}
