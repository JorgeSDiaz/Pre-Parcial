package org.myorg;

import org.myorg.services.RoundRobinService;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.myorg.services.ProxyService.getResourceFromOut;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 *
 */
public class App {
    private static final RoundRobinService roundRobinService = new RoundRobinService(getCantMathServers());

    public static void main( String[] args ) {
        port(getPort());
        get("/", ((request, response) -> {
            response.type("text/html");
            return Files.readAllBytes(Path.of("src/main/resources/static/index.html"));
        }));
        get("/json", ((request, response) -> {
            String url = getMathUrl() + request.queryParams("q");
            System.out.println(url);
            return getResourceFromOut(url);
        }));
    }

    private static Integer getPort() {
        return System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 7654;
    }

    private static Integer getCantMathServers() {
        return System.getenv("CANT-MATH-SERVERS") != null ? Integer.parseInt(System.getenv("CANTMATHSERVERS")) : 1;
    }

    private static String getMathUrl() {
        return System.getenv("MATH-SERVER-" + roundRobinService.getActualServer()) != null ? System.getenv("MATHSERVER" + roundRobinService.getActualServer()) : "http://localhost:4567/math/";
    }
}
