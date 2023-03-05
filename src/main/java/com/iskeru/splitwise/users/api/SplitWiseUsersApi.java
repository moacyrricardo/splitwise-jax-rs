package com.iskeru.splitwise.users.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseUsersApi {

	@GET
	@Path("/get_current_user")
	public SplitWiseUserResponse getCurrentUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey);

}
