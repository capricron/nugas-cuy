package org.cuy.nugas.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cuy.nugas.Model.UserModel;
import org.cuy.nugas.utilities.TemplateResponse;

import io.quarkus.elytron.security.common.BcryptUtil;

@Path("register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterResource {

    @Inject
    EntityManager em;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccount(UserModel newUser) {

        String hashedPassword = BcryptUtil.bcryptHash(newUser.password);
        newUser.password = hashedPassword;
        em.persist(newUser);
        TemplateResponse response = new TemplateResponse("success", "Register Success", newUser);
        return Response.ok(response).build();
    }

}
