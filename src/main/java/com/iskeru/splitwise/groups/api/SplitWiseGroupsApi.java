package com.iskeru.splitwise.groups.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import com.iskeru.splitwise.groups.model.SplitWiseCreateGroup;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseGroupsApi {

	@GET
	@Path("/get_groups")
	public SplitWiseGroupsResponse getGroups(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey);

	@GET
	@Path("/get_group/{id}")
	public SplitWiseGroupResponse getGroup(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey,
			@PathParam("id") Long groupId);

	@POST
	@Path("/create_group")
	public SplitWiseGroupResponse create(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey, SplitWiseCreateGroup createGroup);
}
