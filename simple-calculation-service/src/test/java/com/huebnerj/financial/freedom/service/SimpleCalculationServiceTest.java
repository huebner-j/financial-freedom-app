package com.huebnerj.financial.freedom.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

class SimpleCalculationServiceTest {

  SimpleCalculationService objectUnderTest =
      new SimpleCalculationService(new SimpleCalculationConfiguration());

  @Test
  void financialFreedomWillNeverBeReached() {

    // WHEN
    var result = objectUnderTest.calculate(1000L, 0.1, 300, 10000);

    // THEN
    assertThat(result).isEmpty();
  }

  @Test
  void financialFreedomWillBeReachedIn20Years() {

    // WHEN
    var result = objectUnderTest.calculate(100000L, 10.0, 800, 10000).get();

    // THEN
    assertThat(result.getYearsNeeded()).isEqualTo(20);
  }

  @Test
  void alreadyAchievedFinancialFreedom() {

    // WHEN
    var result = objectUnderTest.calculate(10000000L, 10.0, 800, 10000).get();

    // THEN
    assertThat(result.getYearsNeeded()).isZero();
    assertThat(result.getTotalAmount().doubleValue()).isEqualTo(10000000.0);
    assertThat(result.getInterestAmount()).isZero();
    assertThat(result.getSavedAmount()).isZero();
  }

  @Test
  public void assertThatCalculationParametersAreCorrect() {

    // GIVEN
    long seedCapital = 1000L;
    int monthlySavings = 800;

    // WHEN
    var result = objectUnderTest.calculate(seedCapital, 10.0, monthlySavings, 10000).get();

    // THEN
    assertThat(result.getTotalAmount())
        .isEqualTo(
            BigDecimal.valueOf(
                    seedCapital
                        + result.getInterestAmount().doubleValue()
                        + result.getSavedAmount().doubleValue())
                .setScale(2, RoundingMode.HALF_UP));
  }
}
