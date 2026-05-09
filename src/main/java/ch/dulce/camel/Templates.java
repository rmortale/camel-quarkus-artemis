package ch.dulce.camel;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Templates extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    routeTemplate("upercaseTransformation")
        .templateParameter("inqueue")
        .templateParameter("outqueue")
        .templateParameter("maxConsumers", "5")
        .from("jms:{{inqueue}}?maxConcurrentConsumers={{maxConsumers}}&lazyCreateTransactionManager=false&transacted=true&cacheLevelName=CACHE_CONSUMER")
        .log("Hello ${body}")
        .transform().simple("${uppercase()}")
        .to("jms:{{outqueue}}");
  }

}
