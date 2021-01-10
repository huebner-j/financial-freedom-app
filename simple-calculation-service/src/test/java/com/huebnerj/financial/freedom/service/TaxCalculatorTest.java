package com.huebnerj.financial.freedom.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.huebnerj.financial.freedom.SimpleCalculationConfiguration;
import org.junit.jupiter.api.Test;

class TaxCalculatorTest {

  TaxCalculator objectUnderTest = new TaxCalculator(new SimpleCalculationConfiguration());

  @Test
  void shouldPayZeroTaxesWhenHavingNoInterestIncome() {
    var result = objectUnderTest.calculateTaxes(0.0);

    assertThat(result).isZero();
  }

  @Test
  void shouldPayTaxesOnEveryPennyAboutTheTaxExemption() {
    var result = objectUnderTest.calculateTaxes(1801.0);

    assertThat(result).isEqualTo(263.75);
  }

  @Test
  void shouldPayZeroTaxesWhenExactlyHittingTheTaxExemption() {
    var result = objectUnderTest.calculateTaxes(801.0);

    assertThat(result).isZero();
  }

  @Test
  void shouldPayNoTaxes() {
    var result = objectUnderTest.applyTaxes(801.0);

    assertThat(result).isEqualTo(801.0);
  }

  @Test
  void shouldPayTaxes() {
    var result = objectUnderTest.applyTaxes(1801.0);

    assertThat(result).isEqualTo(1537.25);
  }
}
