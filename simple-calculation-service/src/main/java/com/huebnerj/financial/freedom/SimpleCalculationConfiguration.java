package com.huebnerj.financial.freedom;

import io.dropwizard.Configuration;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SimpleCalculationConfiguration extends Configuration {

  @Max(100)
  @Min(0)
  @NotNull
  private Double tax = 26.375;

  @Min(0)
  @NotNull
  private Long taxExemptionEuro = 801L;

  @Min(0)
  private int maxYears = 100;

  public Double getTax() {
    return tax;
  }

  public SimpleCalculationConfiguration setTax(Double tax) {
    this.tax = tax;
    return this;
  }

  public Long getTaxExemptionEuro() {
    return taxExemptionEuro;
  }

  public SimpleCalculationConfiguration setTaxExemptionEuro(Long taxExemptionEuro) {
    this.taxExemptionEuro = taxExemptionEuro;
    return this;
  }

  public int getMaxYears() {
    return maxYears;
  }

  public SimpleCalculationConfiguration setMaxYears(int maxYears) {
    this.maxYears = maxYears;
    return this;
  }
}
