package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TelefoneDTO;

import br.unitins.topicos1.service.TelefoneService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {
    @Inject
    TelefoneService telefoneService;

    // @POST
    // public Response insert(TelefoneDTO dto, Long idUsuario) {
    //     return Response.status(Status.CREATED).entity(telefoneService.insert(dto)).build();
    // }

    // @POST
    // @Path("/usuario/{idUsuario}")
    // @Transactional
    // public Response insert(@PathParam("idUsuario") Long idUsuario, TelefoneDTO dto) {
    //     return Response.status(Status.CREATED).entity(telefoneService.insert(dto, idUsuario)).build();
    // }


    @POST
    @Path("/cliente/{idCliente}")
    @Transactional
    public Response insert(TelefoneDTO dto) {
        return Response.status(Status.CREATED).entity(telefoneService.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TelefoneDTO dto) {
        telefoneService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefoneService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(telefoneService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(telefoneService.findByAll()).build();
    }

    @GET
    @Path("/cliente/{id}")
    public Response findByIdCliente(@PathParam("id") Long id) {
        return Response.ok(telefoneService.findByIdCliente(id)).build();
    }
}
