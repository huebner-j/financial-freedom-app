package com.huebnerj.financial.freedom.rest.model;

import java.math.BigDecimal;
import java.util.List;

public class FinancialFreedomCalculation {

  private Integer yearsNeeded;

  private BigDecimal totalAmount;
  private BigDecimal interestAmount;
  private BigDecimal savedAmount;
  private BigDecimal taxesPaid;

  private List<YearCalculation> calculation;

  public Integer getYearsNeeded() {
    return yearsNeeded;
  }

  public FinancialFreedomCalculation setYearsNeeded(Integer yearsNeeded) {
    this.yearsNeeded = yearsNeeded;
    return this;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public FinancialFreedomCalculation setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  public BigDecimal getInterestAmount() {
    return interestAmount;
  }

  public FinancialFreedomCalculation setInterestAmount(BigDecimal interestAmount) {
    this.interestAmount = interestAmount;
    return this;
  }

  public BigDecimal getSavedAmount() {
    return savedAmount;
  }

  public FinancialFreedomCalculation setSavedAmount(BigDecimal savedAmount) {
    this.savedAmount = savedAmount;
    return this;
  }

  public FinancialFreedomCalculation setTaxesPaid(BigDecimal taxes) {
    this.taxesPaid = taxes;
    return this;
  }

  public BigDecimal getTaxesPaid() {
    return taxesPaid;
  }
}
