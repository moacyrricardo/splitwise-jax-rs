package com.iskeru.splitwise.expenses.api;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iskeru.splitwise.expenses.model.SplitWiseExpense;
import com.iskeru.splitwise.expenses.model.SplitWiseUpdateExpense;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseExpensesApi {

	@GET
	@Path("/get_expenses")
	public SplitWiseExpensesResponse getExpenses(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@QueryParam("friend_id") Long friendId,
			// 2012-07-27T06:17:09Z
			@QueryParam("dated_after") //
			@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssX") //
			Date after,
			// 2012-07-27T06:17:09Z
			@QueryParam("dated_before") String before);

	@GET
	@Path("/get_expense/{id}")
	public SplitWiseExpenseResponse getExpense(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam(value = "id") Long id);

	@POST
	@Path("/update_expense/{id}")
	public SplitWiseExpensesResponse update(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam(value = "id") Long id, SplitWiseUpdateExpense expense);

}
