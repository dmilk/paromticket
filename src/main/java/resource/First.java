package resource;

import security.jaas.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

/**
 * Created by 1 on 16.05.2014.
 */
@Path("/first")
public class First {

    @GET
    @Path("f")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGreeting(@Context HttpServletRequest req) {

//        List<Todo> todos = new ArrayList<Todo>();
//        todos.addAll(TodoDao.instance.getModel().values());
        Principal principal = req.getUserPrincipal();

        if (principal == null)
            return "gUEST";
        else
            return principal.getName();
    }
}
