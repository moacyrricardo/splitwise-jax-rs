package com.iskeru.splitwise.expenses.api;

import java.util.Date;

import javax.ws.rs.BeanParam;
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
import com.iskeru.splitwise.expenses.model.ExpensesListRequest;
import com.iskeru.splitwise.expenses.model.SplitWiseCreateExpense;
import com.iskeru.splitwise.expenses.model.SplitWiseUpdateExpense;
import com.iskeru.splitwise.utils.SplitWiseConstants;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseExpensesApi {

	@GET
	@Path("/get_expenses")
	public SplitWiseExpensesResponse getExpenses(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@QueryParam("friend_id") Long friendId,//
			@QueryParam("dated_after") //
			@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
			Date after, //
			@QueryParam("dated_before") //
			@JsonFormat(pattern = SplitWiseConstants.DATE_PATTERN) //
			Date before, //
			@QueryParam("group_id") Long groupId);

	@GET
	@Path("/get_expenses")
	public SplitWiseExpensesResponse getExpenses(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@BeanParam ExpensesListRequest request);

	@GET
	@Path("/get_expense/{id}")
	public SplitWiseExpenseResponse getExpense(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam(value = "id") Long id);

	@POST
	@Path("/update_expense/{id}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public SplitWiseExpensesResponse updateWithReceipt(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam(value = "id") Long id, SplitWiseUpdateExpense expense);

//    @PUT
//    @Path("/{expenseId}")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    Expense updateExpense(
//            @PathParam("expenseId") String expenseId,
//            @Multipart(value = "expenseRequest", type = MediaType.APPLICATION_JSON) ExpenseRequest expenseRequest,
//            @Nullable @Multipart(value = "receipt", type = MediaType.APPLICATION_OCTET_STREAM) InputStream receipt);

	@POST
	@Path("/update_expense/{id}")
	public SplitWiseExpensesResponse update(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam(value = "id") Long id, SplitWiseUpdateExpense expense);

	@POST
	@Path("/create_expense")
	public SplitWiseExpensesResponse create(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			SplitWiseCreateExpense expense);

}
