package hu.unideb.inf.meinauto.xml2_meinauto.main;

import org.restlet.Context;
import org.restlet.routing.Router;

public class IMRouter {

    private Router router;
    private String basePath;
    private int port;

    public IMRouter(Context context, String basePath, int port) {
        this.basePath = basePath;
        this.port = port;
        this.router = new Router(context);
        router.setDefaultMatchingQuery(true);
    }

    public void attach(String s, Class clazz) {
        this.router.attach(this.basePath + ":" + String.valueOf(this.port) + s, clazz);
    }

    public Router getRouter() {
        return router;
    }
}
