package ch.dulce.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.builder.Namespaces;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Templates extends RouteBuilder {

    private static final String MESSAGE = """
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

        routeTemplate("extractBodyTransformation")
                .templateParameter("inqueue")
                .templateParameter("outqueue")
                .templateParameter("splitExpr")
                .templateParameter("maxConsumers", "5")
                .from("""
                        jms:{{inqueue}}?maxConcurrentConsumers={{maxConsumers}}
                        &lazyCreateTransactionManager=false
                        &transacted=true
                        &cacheLevelName=CACHE_CONSUMER""")
                .split().xtokenize("{{splitExpr}}", 'u', new Namespaces())
                .log(LoggingLevel.DEBUG, "extractBodyTransformation", "${body}")
                .to("jms:{{outqueue}}");
    }

}
