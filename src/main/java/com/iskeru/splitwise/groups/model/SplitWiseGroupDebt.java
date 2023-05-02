package com.iskeru.splitwise.groups.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SplitWiseGroupDebt {

	private Long from;

	private Long to;

	private BigDecimal amount;

	@JsonProperty("currency_code")
	private String currencyCode;
}
