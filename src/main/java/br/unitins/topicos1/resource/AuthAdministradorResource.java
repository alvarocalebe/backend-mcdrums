package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.LoginDTO;
import br.unitins.topicos1.service.AdministradorService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.Produces;

@Path("/auth/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthAdministradorResource {

    @Inject
    AdministradorService service;

    
    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthAdministradorResource.class);

//    @POST
//     public Response login(@Valid LoginDTO dto) {
        
//         LOG.infof("Iniciando a autenticacao do %s", dto.login());

//         String hashSenha = hashService.getHashSenha(dto.senha());

//         LOG.info("Hash da senha gerado.");

//         LOG.debug(hashSenha);

//         AdministradorResponseDTO result = service.findByLoginAndSenha(dto.login(), hashSenha);

//         if (result != null)
//         LOG.info("Login e senha corretos.");
//     else
//         LOG.info("Login e senha incorretos.");

//         String token = jwtService.generateJwt(result);

//         LOG.info("Finalizando o processo de login.");

//         return Response.ok().header("Authorization", token).build();
//     }
    @POST
    public Response login(LoginDTO dto) {

        LOG.infof("Iniciando a autenticacao do %s", dto.login());

        String hashSenha = hashService.getHashSenha(dto.senha());

        LOG.info("Hash da senha gerado.");

        LOG.debug(hashSenha);

        AdministradorResponseDTO result = service.findByLoginAndSenha(dto.login(), hashSenha);

        if (result != null) {
            // LOG.info("Login e senha corretos.");
            return Response.ok(result)
                    .header("Authorization", jwtService.generateJwt(result)).build();
        } else
            // LOG.info("Login e senha incorretos.");
            return Response.status(Status.NOT_FOUND).entity("login invalido").build();
        // String token = jwtService.generateJwt(result);

        // LOG.info("Finalizando o processo de login.");

        // return Response.ok().header("Authorization", token).build();
    }

}