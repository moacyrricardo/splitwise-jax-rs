package com.iskeru.splitwise.groups.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iskeru.splitwise.groups.model.SplitWiseGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitWiseGroupResponse {
	private SplitWiseGroup group;
}
