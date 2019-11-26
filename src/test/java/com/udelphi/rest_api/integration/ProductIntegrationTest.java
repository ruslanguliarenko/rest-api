package com.udelphi.rest_api.integration;

import java.util.Set;
import com.udelphi.rest_api.dto.CategoryDto;
import com.udelphi.rest_api.dto.ProductDto;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)

class ProductIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }

    @Test
    void shouldReturnProductWithId() {
        ProductDto newProduct = new ProductDto()
                .setName("new product")
                .setDescription("test description")
                .setPrice(2000.2f)
                .setCategories(Set.of(new CategoryDto().setName("category")));

        given()
                .contentType(ContentType.JSON)
                .body(newProduct)
        .when()
                .post("/products")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("new product"))
                .body("description", equalTo("test description"))
                .body("categories.name", hasItem("category"))
                .body("categories.size()", is(1));
    }

    @Test
    void shouldReturnProductById() {
        given()
                .pathParam("id", "1")
        .when()
                .get("/products/{id}")
        .then()
                .body("name", equalTo("Tesla"))
                .body("description", equalTo("Tesla Model S"))
                .body("categories.name", hasItem("car"))
                .body("categories.size()", is(2));
    }

    @Test
    void shouldThrowExceptionWhenGetProductThenIdNotFound() {
        given()
                .pathParam("id", "1000")
        .when()
                .get("/products/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldReturnListCategories() {

        when()
                .get("/products")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(4));
    }

    @Test
    void shouldDeleteProductById() {
        given()
                .pathParam("id", "3")
        .when()
                .delete("/products/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    void shouldUpdateProductById() {

        ProductDto update = new ProductDto()
                .setId(2)
                .setName("updated");

        given()
                .pathParam("id", "2")
                .contentType(ContentType.JSON)
                .body(update)
        .when()
                .put("/products/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateProductThenIdNotFound() {
        ProductDto expected = new ProductDto()
                .setId(1)
                .setName("updated");

        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(expected)
        .when()
                .put("/products/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1000"));
    }

}
