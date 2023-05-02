package com.iskeru.splitwise.groups.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

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
