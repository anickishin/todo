package ru.hh.school.servers.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/")
public class FrontResource {

    private InputStream getFile(String name) {
        File file = new File(name);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getIndex() {
        return getFile("../front/index.html");
    }

    @GET
    @Path("/node_modules/todomvc-app-css/index.css")
    public InputStream getAppCss() {
        return getFile("../front/node_modules/todomvc-app-css/index.css");
    }

    @GET
    @Path("/node_modules/todomvc-common/{file_name}")
    public InputStream getAppCommon(@PathParam("file_name") String fileName) {
        return getFile("../front/node_modules/todomvc-common/" + fileName);
    }

    @GET
    @Path("/js/{file_name}")
    public InputStream getJs(@PathParam("file_name") String fileName) {
        return getFile("../front/js/" + fileName);
    }
}
