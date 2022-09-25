package org.cuy.nugas.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cuy.nugas.Model.UserModel;
import org.cuy.nugas.utilities.TemplateResponse;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    EntityManager em;

    @GET
    public Response getAllUser() {
        List<UserModel> users = em.createQuery("SELECT u FROM UserModel u", UserModel.class).getResultList();

        users.forEach(user -> {
            user.groupTaskList.forEach(group -> {
                group.userList.clear();
            });
        });

        TemplateResponse response = new TemplateResponse("success", "Get All User Success", users);
        return Response.ok(response).build();

    }

}
