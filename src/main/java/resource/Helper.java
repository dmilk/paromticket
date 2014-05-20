package resource;

import security.DAO.Factory;
import security.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

/**
 * Created by 1 on 20.05.2014.
 */
@Path("/help")
public class Helper {
    @GET
    @Path("greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGreeting(@Context HttpServletRequest req) {

        Principal principal = req.getUserPrincipal();

        if (principal == null) {
            return "Guest";
        } else {
            String login = principal.getName();
            User user = Factory.getInstance().getUserDAO().getUserByLogin(login);
            return user.getFirstName() + " " + user.getLastName();
        }

    }
}
