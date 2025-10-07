package com.iskeru.splitwise.friends.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v3.0/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SplitWiseFriendsApi {

	@GET
	@Path("/get_friends")
	public SplitWiseFriendsResponse getFriends(@HeaderParam(HttpHeaders.AUTHORIZATION) String apiKey);

}
