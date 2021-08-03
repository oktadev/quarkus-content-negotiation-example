package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DiceResourceTest {

    @Test
    public void testRollEndpoint() {
        given()
          .when()
                .queryParam("dice", "2d6")
                .get("/roll")
          .then()
                .statusCode(200);
    }

}