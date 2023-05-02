package com.iskeru.splitwise.groups.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iskeru.splitwise.users.model.SplitWiseUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SplitWiseCreateGroup {
	private static final String SEPARATOR = "__";
	private String name;

	@JsonProperty("group_type")
	private Type groupType;

	@JsonIgnore
	private List<SplitWiseUser> users;

	@Builder.Default
	@JsonProperty("simplify_by_default")
	private boolean simplifyByDefault = false;
	
	@JsonAnyGetter
	public Map<String, Object> getProperties() {
		Map<String, Object> a = new HashMap<>();
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				SplitWiseUser user = users.get(i);
				a.put(createKey(user, i, "id"), user.getId());
			}
		}
		return a;
	}

	private String createKey(SplitWiseUser user, int userIndex, String keySuffix) {
		return "users" + SEPARATOR + userIndex + SEPARATOR + keySuffix;
	}
}
