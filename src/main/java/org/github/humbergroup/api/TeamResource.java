package org.github.humbergroup.api;

import com.github.humbergroup.soccer.TeamService;
import com.github.humbergroup.soccer.model.Team;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response getTeamsById(long id){
        return Response.ok().entity(teamService.getTeamById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamsByLeagueId(long id){
        return Response.ok().entity(teamService.getTeamsByLeagueId(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createTeam(Team team){
        teamService.createTeam(team);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createTeamWithLeague(Team team, Long leagueId){
        teamService.createTeamWithLeagueId(team, leagueId);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addTeamIntoLeauge(Long teamId, Long leagueId){
        teamService.addTeamIntoLeague(teamId, leagueId);
        return Response.status(Response.Status.CREATED).entity("Added").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateTeamName(Long teamId, String name){
        teamService.updateTeamName(teamId, name);
        return Response.status(Response.Status.CREATED).entity("Update").build();
    }
}
