package com.iskeru.splitwise.expenses.model;

import java.util.Date;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iskeru.splitwise.utils.SplitWiseConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesListRequest {

	@QueryParam("friend_id")
	private Long friendId;

	@QueryParam("dated_after") //
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
	private Date after;

	@QueryParam("dated_before") //
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
	private Date before;

	@QueryParam("group_id")
	private Long groupId;

	@Builder.Default
	private Integer limit = 20;

	@Builder.Default
	private Integer offset = 0;

	public ExpensesListRequest next() {
		return ExpensesListRequest.builder()//
				.after(getAfter()).before(getBefore())//
				.friendId(getFriendId()).groupId(getGroupId())//
				.limit(getLimit()).offset(getOffset()+getLimit())//
				.build();
	}
}
