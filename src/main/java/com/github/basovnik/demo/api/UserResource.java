package com.github.basovnik.demo.api;

import com.github.basovnik.demo.dal.UserDao;
import com.github.basovnik.demo.domain.model.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.github.basovnik.demo.api.UserResource.PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

@Component
@Produces({APPLICATION_XML, APPLICATION_JSON})
@Path(PATH)
public class UserResource {

    /**
     * Path of user resource.
     */
    public static final String PATH = "/users";

    private final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    public Response getAll() {
        final List<User> users = userDao.getAll();
        final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return ok().header("X-TotalCount", users.size()).entity(entity).build();
    }

    @Path("/{id}")
    @GET
    public Response get(@PathParam("id") String id) {
        final User user = userDao.get(id);
        return user != null ? ok().entity(user).build() : Response.status(NOT_FOUND).build();
    }

    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    @POST
    public Response create(User user) {
        userDao.create(user);
        return Response.status(CREATED).header("Location", PATH + "/" + user.getId()).entity(user).build();
    }

    @Path("/{id}")
    @DELETE
    public Response remove(@PathParam("id") String id) {
        userDao.delete(id);
        return noContent().build();
    }
}
