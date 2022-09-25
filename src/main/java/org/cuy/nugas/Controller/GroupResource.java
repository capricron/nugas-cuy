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

import org.cuy.nugas.Model.GroupTaskModel;
import org.cuy.nugas.utilities.TemplateResponse;

@Path("group")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

    @Inject
    EntityManager em;

    @GET
    public Response getAllGroup() {
        List<GroupTaskModel> groups = em.createQuery("SELECT g FROM GroupTaskModel g", GroupTaskModel.class)
                .getResultList();

        for (GroupTaskModel group : groups) {
            group.userList.forEach(user -> {
                user.taskList.clear();
            });
        }
        TemplateResponse response = new TemplateResponse("success", "Get All Group Success", groups);

        return Response.ok(response).build();
    }

}
