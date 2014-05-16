package jersey;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/order")
public class MyApp extends ResourceConfig {
    public MyApp() {
        //super(TodosResource.class);
        register(RolesAllowedDynamicFeature.class);
        packages("resource");
    }
}