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

        TemplateResponse response = new TemplateResponse("success", "Get All Group Success", groups);

        return Response.ok(response).build();
    }

    @GET
    @Path("{id}")
    public Response getGroupById(@PathParam("id") Long id) {
        GroupTaskModel group = em.find(GroupTaskModel.class, id);

        if (group == null) {
            TemplateResponse response = new TemplateResponse("error", "Group Not Found", null);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }

        TemplateResponse response = new TemplateResponse("success", "Get Group By Id Success", group);
        return Response.ok(response).build();
    }

    @POST
    @Transactional
    public Response createGroup(GroupTaskModel group) {
        em.persist(group);
        TemplateResponse response = new TemplateResponse("success", "Create Group Success", group);
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateGroup(@PathParam("id") Long id, GroupTaskModel group) {
        try {
            GroupTaskModel groupTask = em.find(GroupTaskModel.class, id);
            groupTask.groupName = group.groupName;
            em.persist(groupTask);

            TemplateResponse response = new TemplateResponse("success", "Update Group Success", groupTask);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "Update Group Failed", null);
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteGroup(@PathParam("id") Long id) {
        try {
            GroupTaskModel group = em.find(GroupTaskModel.class, id);
            em.remove(group);
            TemplateResponse response = new TemplateResponse("success", "Delete Group Success", group);
            return Response.ok(response).build();
        } catch (Exception e) {
            TemplateResponse response = new TemplateResponse("error", "Delete Group Failed", null);
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

}
