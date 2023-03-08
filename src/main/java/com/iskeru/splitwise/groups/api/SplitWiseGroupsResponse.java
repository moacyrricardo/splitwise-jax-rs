package com.iskeru.splitwise.groups.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iskeru.splitwise.groups.model.SplitWiseGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitWiseGroupsResponse {
	private List<SplitWiseGroup> groups;
}
