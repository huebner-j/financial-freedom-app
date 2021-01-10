package com.huebnerj.financial.freedom.service;

import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;

public class TaxCalculator {

  private final SimpleCalculationConfiguration configuration;

  TaxCalculator(SimpleCalculationConfiguration configuration) {
    this.configuration = configuration;
  }

  public double calculateTaxes(double interestAmount) {

    return interestAmount > configuration.getTaxExemptionEuro()
        ? (interestAmount - configuration.getTaxExemptionEuro()) * (configuration.getTax() / 100)
        : 0.0;
  }

  public double applyTaxes(double interestAmount) {
    return interestAmount - calculateTaxes(interestAmount);
  }
}
