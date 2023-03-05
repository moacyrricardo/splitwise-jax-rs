package com.iskeru.splitwise.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iskeru.splitwise.users.model.SplitWiseUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SplitWiseExpenseUser {
	@JsonProperty("user_id")
	private Long userId;
	private SplitWiseUser user;
	@JsonProperty("paid_share")
	private BigDecimal paidShare;
	@JsonProperty("owed_share")
	private BigDecimal owedShare;
	@JsonProperty("net_balance")
	private BigDecimal netBalance;
}