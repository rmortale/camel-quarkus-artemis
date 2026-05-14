package ch.dulce.camel;


import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

@ApplicationScoped
public class CamelRoutes extends EndpointRouteBuilder {

  @Override
  public void configure() throws Exception {

//    from(jms("inqueue")
//        .transacted(true)
//        .cacheLevelName("CACHE_CONSUMER")
//        .maxConcurrentConsumers(5)
//        .advanced().lazyCreateTransactionManager(false))
//        .log("${body}");


    // templatedRoute("extractBodyTransformation")
    // .parameter("inqueue", "extractin")
    // .parameter("outqueue", "extractout")
    // .parameter("splitExpr", "/company/address");
  }

}
