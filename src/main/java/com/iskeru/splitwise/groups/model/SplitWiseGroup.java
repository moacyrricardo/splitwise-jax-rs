package com.iskeru.splitwise.groups.model;

import java.util.List;

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
public class SplitWiseGroup {
	private Long id;
	private String name;
	@JsonProperty("group_type")
	private String groupType;
	private List<SplitWiseUser> members;
}