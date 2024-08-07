package com.iskeru.splitwise.expenses.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iskeru.splitwise.groups.model.SplitWiseGroup;
import com.iskeru.splitwise.users.model.SplitWiseUser;
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
	
	public static final List<SplitWiseExpenseUser> buildUsersFromGroupEqually(SplitWiseUser user, SplitWiseGroup group,
			BigDecimal value) {
		BigDecimal cost = value.abs();

		BigDecimal userCount = new BigDecimal(group.getMembers().size());
		BigDecimal countOtherUsers = userCount.subtract(BigDecimal.ONE);

		BigDecimal costPerUser = cost.divide(userCount).setScale(2, RoundingMode.FLOOR);

		BigDecimal otherUsersCostSum = costPerUser.multiply(countOtherUsers);
		BigDecimal userCost = cost.subtract(otherUsersCostSum);

		List<SplitWiseExpenseUser> users = new ArrayList<>();

		//mutually exclusive, variables are here to make code below more readable
		boolean isExpense = value.signum() == -1;
		boolean isRefund = value.signum() == 1;

		if (isExpense) {
			users.add(SplitWiseExpenseUser.builder().userId(user.getId()).paidShare(cost).owedShare(userCost).build());
		}
		if (isRefund) {
			users.add(SplitWiseExpenseUser.builder().userId(user.getId()).paidShare(userCost).owedShare(cost).build());
		}

		for (SplitWiseUser usr : group.getMembers()) {
			if (usr.getId().equals(user.getId())) {
				continue;
			}

			if (isExpense) {
				users.add(SplitWiseExpenseUser.builder().userId(usr.getId()).paidShare(BigDecimal.ZERO).owedShare(costPerUser).build());
			}
			if (isRefund) {
				users.add(SplitWiseExpenseUser.builder().userId(usr.getId()).paidShare(userCost).owedShare(BigDecimal.ZERO).build());
			}
		}
		return users;
	}
}
