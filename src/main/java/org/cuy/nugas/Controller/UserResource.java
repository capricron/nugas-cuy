package org.cuy.nugas.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cuy.nugas.Model.UserModel;
import org.cuy.nugas.utilities.TemplateResponse;

import io.quarkus.elytron.security.common.BcryptUtil;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    EntityManager em;

    @GET
    public Response getAllUser() {
        List<UserModel> users = em.createQuery("SELECT u FROM UserModel u", UserModel.class).getResultList();

        TemplateResponse response = new TemplateResponse("success", "Get All User Success", users);
        return Response.ok(response).build();

    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") Long id) {
        UserModel user = em.find(UserModel.class, id);

        if (user == null) {
            TemplateResponse response = new TemplateResponse("error", "User Not Found", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        TemplateResponse response = new TemplateResponse("success", "Get User By Id Success", user);
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, UserModel user) {
        try {
            UserModel userUpdate = em.find(UserModel.class, id);
            userUpdate.username = user.username;
            userUpdate.email = user.email;
            userUpdate.password = BcryptUtil.bcryptHash(user.password);
            em.persist(userUpdate);
            TemplateResponse response = new TemplateResponse("success", "Update User Success", userUpdate);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "User Not Found", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            UserModel user = em.find(UserModel.class, id);
            em.remove(user);
            TemplateResponse response = new TemplateResponse("success", "Delete User Success", user);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "User Not Found", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }

}
