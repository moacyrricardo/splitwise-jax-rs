package com.iskeru.splitwise.expenses.model;

import java.util.Date;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iskeru.splitwise.utils.SplitWiseConstants;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpensesListRequest {

	@QueryParam("friend_id")
	Long friendId;
	@QueryParam("dated_after") //
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
	Date after;
	@QueryParam("dated_before") //
	@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
	Date before;

	@QueryParam("group_id")
	Long groupId;

	private Integer limit;

	private Integer offset;

	public ExpensesListRequest next() {
		return ExpensesListRequest.builder()//
				.after(getAfter()).before(getBefore())//
				.friendId(getFriendId()).groupId(getGroupId())//
				.limit(getLimit()).offset(getOffset()+getLimit()+1)//
				.build();
	}
}
