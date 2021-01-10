package com.huebnerj.financial.freedom.service;

import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;
import com.huebnerj.financial.freedom.rest.model.FinancialFreedomCalculation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class SimpleCalculationService {

  private final SimpleCalculationConfiguration configuration;
  private final TaxCalculator taxCalculator;

  public SimpleCalculationService(SimpleCalculationConfiguration configuration) {
    this.configuration = configuration;
    this.taxCalculator = new TaxCalculator(configuration);
  }

  /**
   * @param seedCapital - the capital the individual owns in the beginning
   * @param interest - the interest the individual tries to achieve. Pattern: 100% is represented as
   *     100.0
   * @param monthlySavingAmount - the amount the individual expects to save every month
   * @param desiredMonthlyIncome - the desired income the individual needs in order to claim
   *     financial freedom
   * @return a detailed calculation report until the individual achieves financial freedom
   */
  public Optional<FinancialFreedomCalculation> calculate(
      Long seedCapital,
      Double interest,
      Integer monthlySavingAmount,
      Integer desiredMonthlyIncome) {

    double totalAmount = seedCapital;
    double totalSavedAmount = 0.0;
    double totalInterestAmount = 0.0;
    double totalTaxesPaid = 0.0;

    int yearsNeeded = 0;
    var interestThisYear = taxCalculator.applyTaxes(totalAmount * (interest / 100));
    while (interestThisYear / 12 <= desiredMonthlyIncome) {

      if (yearsNeeded > configuration.getMaxYears()) {
        return Optional.empty();
      }
      ++yearsNeeded;
      var taxFreeInterestThisYear = totalAmount * (interest / 100);
      interestThisYear = taxCalculator.applyTaxes(taxFreeInterestThisYear);

      totalTaxesPaid = totalTaxesPaid + taxCalculator.calculateTaxes(taxFreeInterestThisYear);
      totalInterestAmount = totalInterestAmount + interestThisYear;
      totalSavedAmount = totalSavedAmount + monthlySavingAmount * 12;
      totalAmount = totalAmount + interestThisYear + monthlySavingAmount * 12;
    }

    return Optional.of(
        new FinancialFreedomCalculation()
            .setYearsNeeded(yearsNeeded)
            .setTotalAmount(convertToMoneyUnit(totalAmount))
            .setInterestAmount(convertToMoneyUnit(totalInterestAmount))
            .setSavedAmount(convertToMoneyUnit(totalSavedAmount))
            .setTaxesPaid(convertToMoneyUnit(totalTaxesPaid)));
  }

  private static BigDecimal convertToMoneyUnit(double totalInterestAmount) {
    return BigDecimal.valueOf(totalInterestAmount).setScale(2, RoundingMode.HALF_UP);
  }
}
