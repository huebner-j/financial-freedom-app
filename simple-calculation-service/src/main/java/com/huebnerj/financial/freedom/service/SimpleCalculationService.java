package com.huebnerj.financial.freedom.service;

import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;
import com.huebnerj.financial.freedom.rest.model.FinancialFreedomCalculation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class SimpleCalculationService {

  private final SimpleCalculationConfiguration configuration;

  public SimpleCalculationService(SimpleCalculationConfiguration configuration) {
    this.configuration = configuration;
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
    int yearsNeeded = 0;
    while (totalAmount * (interest / 100) / 12 <= desiredMonthlyIncome) {

      if (yearsNeeded > configuration.getMaxYears()) {
        return Optional.empty();
      }
      ++yearsNeeded;
      totalInterestAmount = totalInterestAmount + totalAmount * (interest / 100);
      totalSavedAmount = totalSavedAmount + monthlySavingAmount * 12;
      totalAmount = totalAmount * (1 + interest / 100) + monthlySavingAmount * 12;
    }

    return Optional.of(
        new FinancialFreedomCalculation()
            .setYearsNeeded(yearsNeeded)
            .setTotalAmount(BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP))
            .setInterestAmount(
                BigDecimal.valueOf(totalInterestAmount).setScale(2, RoundingMode.HALF_UP))
            .setSavedAmount(
                BigDecimal.valueOf(totalSavedAmount).setScale(2, RoundingMode.HALF_UP)));
  }
}
