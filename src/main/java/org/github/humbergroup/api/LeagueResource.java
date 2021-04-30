package org.github.humbergroup.api;

import com.github.humbergroup.soccer.LeagueService;
import com.github.humbergroup.soccer.model.League;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/leagues")
public class LeagueResource {

    @EJB
    private LeagueService leagueService;

    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service is working").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createLeague(League league){
        return Response.status(Response.Status.CREATED).entity(leagueService.create(league)).build();
    }


}
