package com.iskeru.splitwise.friends.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iskeru.splitwise.users.model.SplitWiseUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitWiseFriendsResponse {
	private List<SplitWiseUser> friends;
}
