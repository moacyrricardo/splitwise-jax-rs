package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iskeru.splitwise.utils.SplitWiseConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SplitWiseCreateExpense {
	private static final String SEPARATOR = "__";
	@JsonProperty("group_id")
	@Builder.Default
	private Long groupId = SplitWiseConstants.NO_GROUP;
	private BigDecimal cost;
	private String description;
	private String details;
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN)
	private Date date;

	@JsonIgnore
	private List<SplitWiseExpenseUser> users;

	// repeat_interval="never"
	@JsonProperty("repeat_interval")
	private Repeat repeatInterval;
	// currency_code="USD"
	@JsonProperty("currency_code")
	private String currencyCode;

	@JsonProperty("split_equally")
	private Boolean splitEqually;

	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		Map<String, Object> a = new HashMap<>();
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				SplitWiseExpenseUser user = users.get(i);
				a.put(createKey(user, i, "user_id"), user.getUserId());
				a.put(createKey(user, i, "paid_share"), user.getPaidShare().toString());
				a.put(createKey(user, i, "owed_share"), user.getOwedShare().toString());
			}
		}
		return a;
	}

	private String createKey(SplitWiseExpenseUser user, int userIndex, String keySuffix) {
		return "users" + SEPARATOR + userIndex + SEPARATOR + keySuffix;
	}
}
