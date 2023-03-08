package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iskeru.splitwise.utils.SplitWiseConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SplitWiseUpdateExpense {
	private static final String SEPARATOR = "__";
	private Long id;
	@Builder.Default
	private Long group_id = SplitWiseConstants.NO_GROUP;
	private BigDecimal cost;
	private String description;
	private String details;

	@JsonIgnore
	private List<SplitWiseExpenseUser> users;

	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		Map<String, Object> a = new HashMap<>();
		for (int i = 0; i < users.size(); i++) {
			SplitWiseExpenseUser user = users.get(i);
			a.put(createKey(user, i, "user_id"), user.getUserId());
			a.put(createKey(user, i, "paid_share"), user.getPaidShare().toString());
			a.put(createKey(user, i, "owed_share"), user.getOwedShare().toString());
		}
		return a;
	}

	private String createKey(SplitWiseExpenseUser user, int userIndex, String keySuffix) {
		return "users" + SEPARATOR + userIndex + SEPARATOR + keySuffix;
	}
}
