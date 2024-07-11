package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.AlterarLoginDTO;
import br.unitins.topicos1.dto.AlterarNomeDTO;
import br.unitins.topicos1.dto.AlterarSenhaDTO;
// import org.eclipse.microprofile.jwt.Claim;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.ClienteService;
// import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    // @Inject
    // @Claim("sub")
    // Long idCliente;

    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService service;

    @POST
    public Response insert(@Valid ClienteDTO dto) {
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    // @PATCH
    // @Path("/alterar-senha/{id}")
    // public Response alterarSenha(@Valid AlterarSenhaDTO dto, @PathParam("id")
    // Long id) {
    // try {

    // service.alterarSenha(dto, id);

    // return Response.noContent().build();
    // } catch (Exception e) {

    // return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    // .entity("Erro interno do servidor ao alterar a senha.").build();
    // }
    // }

    @PATCH
    @Path("/alterar-senha")
    public Response alterarSenha(@Valid AlterarSenhaDTO dto) {
        try {
            String login = jwt.getSubject();
            service.alterarSenha(dto, login);

            return Response.noContent().build();
        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar a senha.").build();
        }
    }

    @GET
    @Path("/enderecos")
    public Response getEnderecos() {
        String login = jwt.getSubject();
        return Response.ok(service.findByLogin(login).listaEndereco()).build();
    }

    @PATCH
    @Path("/alterar-nome/{id}")
    public Response alterarNome(@Valid AlterarNomeDTO dto, @PathParam("id") Long id) {
        try {

            service.alterarNome(dto, id);

            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar o nome.").build();
        }
    }

    @PATCH
    @Path("/alterar-login/{id}")
    public Response alterarLogin(@Valid AlterarLoginDTO dto, @PathParam("id") Long id) {
        try {

            service.alterarLogin(dto, id);

            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar o login.").build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(ClienteDTO dto, @PathParam("id") Long idCliente) {
        service.update(dto, idCliente);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/login/{login}")
    public Response findByLogin(@PathParam("login") String login) {
        return Response.ok(service.findByLogin(login)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/busca/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByName(nome)).build();
    }

    @GET
    public Response findAll() {

        return Response.ok(service.getAll()).build();
    }

    @PATCH
    @Path("/{id}/updateTelefones")
    public Response updateTelefones(@PathParam("id") Long id, List<TelefoneDTO> newTelefones) {
        try {
            service.updateTelefones(id, newTelefones);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}