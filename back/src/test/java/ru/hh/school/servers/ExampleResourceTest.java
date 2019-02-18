/*package ru.hh.school.servers;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.servers.resource.ToDoResource;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = ExampleTestConfig.class)
public class ExampleResourceTest extends NabTestBase {

  @Test
  public void hello() {
    final String name = "test";
    Response response = target("/")
        .queryParam("name", name)
        .request()
        .get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(String.format("Hello, %s!", name), response.readEntity(String.class));
  }

  @Test
  public void helloWithoutParams() {
    Response response = createRequest("/").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals("Hello, world!", response.readEntity(String.class));
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().registerResources(ToDoResource.class).bindToRoot().build();
  }
}
*/
