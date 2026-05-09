package ch.dulce.camel;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // from("jms:quarkusin?lazyCreateTransactionManager=false&transacted=true&cacheLevelName=CACHE_CONSUMER")
    //     .log("Hello ${body}")
    //     .transform().simple("${uppercase()}")
    //     .to("jms:quarkusout")
    //     .throwException(new Exception("test exception"));

  }

}
