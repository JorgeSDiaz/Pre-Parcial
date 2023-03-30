package org.myorg;

import static spark.Spark.*;

import static org.myorg.services.PalindromeService.*;

public class MathApp {
    public static void main(String[] args) {
        port(getPort());
        get("/math/:word", (request, response) -> {
            response.type("application/json");
            return "{" +
                    "\"operation\": \"palindromo\"," +
                    "\"input\": \"" + request.params("word") + "\"," +
                    "\"output\": \"" + isPalindrome(request.params("word")) +
                    "\"}";
        });
    }

    public static Integer getPort() {
        return System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 4567;
    }
}
