package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.service.EnderecoService;
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

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {
    @Inject
    EnderecoService enderecoService;

    @POST
    @Path("/cliente/{idCliente}")
    @Transactional
    // public Response insert(EnderecoDTO dto, @PathParam("idUsuario") Long idUsuario) {
        public Response insert(EnderecoDTO dto) {
        return Response.status(Status.CREATED).entity(enderecoService.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EnderecoDTO dto) {
        enderecoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        enderecoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(enderecoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(enderecoService.findByAll()).build();
    }

    @GET
    @Path("/cliente/{id}")
    public Response findByIdCliente(@PathParam("id") Long id) {
        return Response.ok(enderecoService.findByIdCliente(id)).build();
    }
}
