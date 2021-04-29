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

    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") long id) {
        playerService.delete(id);
        return Response.ok().entity("Player deleted").build();
    }



    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers(Long teamId) {
        return Response.ok().entity(playerService.getPlayersByTeamId(teamId)).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(Long id, Player player) {
        if (player.getId() == null || player.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Player playerToUpdate = playerService.getPlayerById(player.getId());
        if (playerToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        playerService.update(id, player);
        return Response.ok().entity("Player updated").build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player, Long teamId) {
        playerService.createPlayerWithTeamId(player, teamId);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }
}
