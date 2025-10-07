package com.iskeru.splitwise.users.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseUsersApi {

	@GET
	@Path("/get_current_user")
	public SplitWiseUserResponse getCurrentUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey);

}
