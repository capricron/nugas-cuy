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

import org.cuy.nugas.Model.TaskModel;
import org.cuy.nugas.utilities.TemplateResponse;

@Path("task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    EntityManager em;

    @GET
    public Response getAllTask() {
        List<TaskModel> tasks = em.createQuery("SELECT t FROM TaskModel t", TaskModel.class).getResultList();
        TemplateResponse response = new TemplateResponse("success", "Get All Task Success", tasks);
        return Response.ok(response).build();
    }

}
