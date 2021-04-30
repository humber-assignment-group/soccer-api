package org.github.humbergroup.api;

import com.github.humbergroup.soccer.TeamService;
import com.github.humbergroup.soccer.model.Team;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/teams")
public class TeamResource {

    @EJB
    private TeamService teamService;

    @GET
    @Path("/ping")
    public Response ping(){
        return Response.ok().entity("Service is working").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getTeamsById(@PathParam("id") long id){
        return Response.ok().entity(teamService.getTeamById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getByLeague")
    public Response getTeamsByLeagueId(@QueryParam("leagueId") long id){
        return Response.ok().entity(teamService.getTeamsByLeagueId(id)).build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createTeam(Team team){
        return Response.status(Response.Status.CREATED).entity(teamService.createTeam(team)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/createTeamWithLeague")
    public Response createTeamWithLeague(Team team, Long leagueId){
        return Response.status(Response.Status.CREATED).entity(teamService.createTeam(team)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/addTeam")
    public Response addTeamIntoLeague(@QueryParam("teamId")Long teamId, @QueryParam("leagueId")Long leagueId){
        teamService.addTeamIntoLeague(teamId, leagueId);
        return Response.status(Response.Status.CREATED).entity("Added").build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/update")
    public Response updateTeamName(@QueryParam("teamId")Long teamId, @QueryParam("name")String name){
        teamService.updateTeamName(teamId, name);
        return Response.status(Response.Status.CREATED).entity("Update").build();
    }
}
