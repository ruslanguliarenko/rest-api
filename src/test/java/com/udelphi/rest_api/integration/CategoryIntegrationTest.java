package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.dto.CategoryDto;
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

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CategoryIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldReturnCategoryWithId() {
        CategoryDto newCategory = new CategoryDto()
                .setName("laptop");

        given()
                .contentType(ContentType.JSON)
                .body(newCategory)
        .when()
                .post("/categories")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("laptop"))
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnCategoryById() {
        given()
                .pathParam("id", "2")
        .when()
                .get("/categories/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("bike"));
    }

    @Test
    void shouldThrowExceptionWhenGetCategoryThenIdNotFound() {
        given()
                .pathParam("id", "1000")
        .when()
                .get("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldReturnListCategories() {

        when()
                .get("/categories")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(4));

    }

    @Test
    void shouldDeleteCategoryById() {
        given()
                .pathParam("id", "4")
        .when()
                .delete("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenDeleteCategoryThenIdNotFound() {
        given()
                .pathParam("id", "1000")
        .when()
                .delete("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldUpdateCategoryById() {

        CategoryDto update = new CategoryDto()
                .setName("updated")
                .setId(1);

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(update)
        .when()
                .put("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateCategoryThenIdNotFound() {
        CategoryDto expected = new CategoryDto()
                .setName("laptop")
                .setId(10);

        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(expected)
        .when()
                .put("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));

    }

}
