package org.cuy.nugas.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cuy.nugas.Model.TaskModel;
import org.cuy.nugas.Model.UserModel;
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

    @GET
    @Path("{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        TaskModel task = em.find(TaskModel.class, id);

        if (task == null) {
            TemplateResponse response = new TemplateResponse("error", "Task Not Found", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        TemplateResponse response = new TemplateResponse("success", "Get Task By Id Success", task);
        return Response.ok(response).build();
    }

    @POST
    @Path("{id}")
    @Transactional
    public Response createTask(TaskModel task, @PathParam("id") Long userId) {
        UserModel user = em.find(UserModel.class, userId);
        task.user = user;
        em.persist(task);
        TemplateResponse response = new TemplateResponse("success", "Create Task Success", task);
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, TaskModel newtask) {
        try {
            TaskModel task = em.find(TaskModel.class, id);
            task.name = newtask.name;
            task.note = newtask.note;
            task.date = newtask.date;
            task.time = newtask.time;
            em.persist(task);
            TemplateResponse response = new TemplateResponse("success", "Update Task Success", task);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "Update Task Failed", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }

    @PUT
    @Path("{id}/done")
    @Transactional
    public Response updateTaskDone(@PathParam("id") Long id, TaskModel newtask) {
        try {
            TaskModel task = em.find(TaskModel.class, id);
            task.done = newtask.done;
            em.persist(task);
            TemplateResponse response = new TemplateResponse("success", "Update Task Done Success", task);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "Update Task Done Failed", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        try {
            TaskModel task = em.find(TaskModel.class, id);
            em.remove(task);
            TemplateResponse response = new TemplateResponse("success", "Delete Task Success", task);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "Delete Task Failed", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }

}
