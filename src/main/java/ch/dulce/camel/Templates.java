package ch.dulce.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Templates extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    routeTemplate("uppercaseTransformation")
        .templateParameter("inqueue")
        .templateParameter("outqueue")
        .templateParameter("maxConsumers", "5")
        .from("""
            jms:{{inqueue}}?maxConcurrentConsumers={{maxConsumers}}
            &lazyCreateTransactionManager=false
            &transacted=true
            &cacheLevelName=CACHE_CONSUMER""")
        .log(LoggingLevel.DEBUG, "uppercaseTransformation", "${body}")
        .transform().simple("${uppercase()}")
        .to("jms:{{outqueue}}");
  }

}
