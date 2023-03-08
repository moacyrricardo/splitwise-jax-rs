package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'")
	private Date date;

	private List<SplitWiseExpenseUser> users;

	private List<SplitWiseRepayment> repayments;

	public LocalDateTime getDateTime() {
		return date.toInstant().atZone(ZoneId.of("GMT")).toLocalDateTime();
	}
}