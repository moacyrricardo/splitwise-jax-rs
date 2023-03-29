package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iskeru.splitwise.utils.SplitWiseConstants;

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
	private String details;

	@JsonProperty("group_id")
	private Long groupId = SplitWiseConstants.NO_GROUP;

	@JsonProperty("user_id")
	private Long userId;

	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date date;

	@JsonProperty("repeat_interval")
	private Repeat repeatInterval;

	private List<SplitWiseExpenseUser> users;

	private List<SplitWiseRepayment> repayments;

	@JsonProperty("deleted_at")
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date deletedAt;

	public LocalDateTime getDateTime() {
		return date.toInstant().atZone(ZoneId.of("GMT")).toLocalDateTime();
	}

	public boolean isDeleted() {
		return Objects.nonNull(getDeletedAt());
	}

	public boolean isNotDeleted() {
		return !isDeleted();
	}
}