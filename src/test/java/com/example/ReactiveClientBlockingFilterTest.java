package com.example;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
class ReactiveClientBlockingFilterTest {

    @Test
    void testString() {
        given()
                .when().get("/hello/string")
                .then()
                .statusCode(500);
    }

    @Test
    void testInputStream() {
        given()
                .when().get("/hello/inputStream")
                .then()
                .statusCode(500);
    }

}
