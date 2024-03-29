package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

	@JsonProperty("created_at")
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date createdAt;

	@JsonProperty("updated_at")
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date updatedAt;

	@JsonProperty("deleted_at")
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date deletedAt;

	/**
	 * LocalDateTime as GMT
	 * @return the {@link SplitWiseExpense#getDate()} as an {{@link OffsetDateTime}}
	 */
	public OffsetDateTime getDateAsOffsetDateTime() {
		return date.toInstant().atZone(ZoneId.of("GMT")).toOffsetDateTime();
	}

	/**
	 * LocalDateTime on machine's zone via {@link ZoneId#systemDefault()}
	 * @return the {@link SplitWiseExpense#getDate()} as an {@link LocalDateTime}
	 */
	public LocalDateTime getDateAsLocalDateTime() {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public boolean isDeleted() {
		return Objects.nonNull(getDeletedAt());
	}

	public boolean isNotDeleted() {
		return !isDeleted();
	}
}
