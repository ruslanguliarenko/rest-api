package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.dto.RoleDto;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoleIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }


    @Test
    void shouldReturnRoleWithId() {
        RoleDto newRole = new RoleDto()
                .setName("admin");

        given()
                .contentType(ContentType.JSON)
                .body(newRole)
                .when()
                .post("/roles")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("admin"))
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnRoleById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/roles/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("client"));
    }

    @Test
    void shouldThrowExceptionWhenGetRoleThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldReturnListRoles() {

        when()
                .get("/roles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(3));

    }

    @Test
    void shouldDeleteRoleById() {
        given()
                .pathParam("id", "2")
                .when()
                .delete("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenDeleteRoleThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .when()
                .delete("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldUpdateRoleById() {

        RoleDto update = new RoleDto()
                .setName("updated")
                .setId(1);

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(update)
                .when()
                .put("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateRoleThenIdNotFound() {
        RoleDto expected = new RoleDto()
                .setName("laptop")
                .setId(10);

        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(expected)
                .when()
                .put("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

}
