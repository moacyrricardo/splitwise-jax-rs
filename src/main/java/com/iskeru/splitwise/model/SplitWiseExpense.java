package com.iskeru.splitwise.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitWiseExpense {
	private Long id;
	private BigDecimal cost;
	@JsonProperty("currency_code")
	private String currency;
	private String description;

	@JsonProperty("group_id")
	private Long groupId;
	@JsonProperty("user_id")
	private Long userId;

	private List<SplitWiseExpenseUser> users;

	private List<SplitWiseRepayment> repayments;
}