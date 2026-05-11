package ch.dulce.camel;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // templatedRoute("extractBodyTransformation")
    // .parameter("inqueue", "extractin")
    // .parameter("outqueue", "extractout")
    // .parameter("splitExpr", "/company/address");
  }

}
