package ch.dulce.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Templates extends RouteBuilder {

  static final String MESSAGE = """
      <object>
        <header>
          <system>${header.system}</system>
          <serviceid>${header.serviceid}</serviceid>
        </header>
        <body>${body}</body>
      </object>
      """;

  @Override
  public void configure() throws Exception {

    routeTemplate("wrapbodyTransformation")
        .templateParameter("inqueue")
        .templateParameter("outqueue")
        .templateParameter("maxConsumers", "5")
        .from("""
            jms:{{inqueue}}?maxConcurrentConsumers={{maxConsumers}}
            &lazyCreateTransactionManager=false
            &transacted=true
            &cacheLevelName=CACHE_CONSUMER""")
        .transform().simple(MESSAGE)
        .log(LoggingLevel.DEBUG, "wrapbodyTransformation", "${body}")
        .to("jms:{{outqueue}}");


    routeTemplate("uppercaseTransformation")
        .templateParameter("inqueue")
        .templateParameter("outqueue")
        .templateParameter("maxConsumers", "5")
        .from("""
            jms:{{inqueue}}?maxConcurrentConsumers={{maxConsumers}}
            &lazyCreateTransactionManager=false
            &transacted=true
            &cacheLevelName=CACHE_CONSUMER""")
        .transform().simple("${uppercase()}")
        .log(LoggingLevel.DEBUG, "uppercaseTransformation", "${body}")
        .to("jms:{{outqueue}}");
  }

}
