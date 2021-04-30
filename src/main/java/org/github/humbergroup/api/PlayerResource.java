package org.github.humbergroup.api;

import com.github.humbergroup.soccer.PlayerService;
import com.github.humbergroup.soccer.model.Player;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service is working").build();
    }

    /**
     * delete Player by Id
     * Delete /players/{id}
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") long id) {
        playerService.delete(id);
        return Response.ok().entity("Player deleted").build();
    }

    /**
     * retrieve Player by Id
     * Get /players/{id}
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({APPLICATION_JSON})
    public Response getPlayer(@PathParam("id") long id) {
        return Response.ok().entity(playerService.getPlayerById(id)).build();
    }

    /**
     * retrieve Players which belong to {teamId} team
     * Get /players?teamId={teamId}
     *
     * @param teamId
     * @return
     */
    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers(@QueryParam("teamId") Long teamId) {
        return Response.ok().entity(playerService.getPlayersByTeamId(teamId)).build();
    }

    /**
     * update Player
     * Put /players
     * {
     *      *     "id": 1
     *     "firstName":"kobe",
     *     "lastName": "bryant",
     *     "dateOfBirth": "1978-08-23"
     * }
     *
     * @param player
     * @return
     */
    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(Player player) {
        playerService.update(player);
        return Response.ok().entity("Player updated").build();
    }

    /**
     * create a Player
     * Post /players
     * {
     *     "firstName":"kobe",
     *     "lastName": "bryant",
     *     "dateOfBirth": "1978-08-23"
     * }
     *
     * @param player
     * @return
     */
    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        return Response.status(Response.Status.CREATED).entity(playerService.createPlayer(player)).build();
    }

    /**
     * add player to team
     * Post /players/add_player_to_team?playerId={playerId}&teamId={teamId}
     *
     * @param playerId
     * @param teamId
     * @return
     */
    @POST
    @Produces(TEXT_PLAIN)
    @Path("add_player_to_team")
    public Response addPlayerToTeam(@QueryParam("playerId") Long playerId, @QueryParam("teamId") Long teamId){
        playerService.addPlayerIntoTeam(playerId, teamId);
        return Response.status(Response.Status.CREATED).entity("added").build();
    }
}
