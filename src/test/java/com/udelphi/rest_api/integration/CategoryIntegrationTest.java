package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Category;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CategoryIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    @Sql("/category/create_categories.sql")
    public void shouldReturnCategoryWithId(){
        Category expected = new Category("car");

        given()
                .contentType(ContentType.JSON)
                .body(expected)
        .when()
                .post("/categories")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id",  notNullValue());
    }

    @Test
    @Sql({"/category/create_categories.sql", "/category/insert_categories.sql"})
    public void shouldReturnCategoryById(){
        given()
                .pathParam("id", "1")
        .when()
                .get("/categories/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("car"))
                .body("id", equalTo(1));
    }

    @Test
    @Sql("/category/create_categories.sql")
    public void shouldThrowExceptionWhenGetCategoryThenIdNotFound(){
        given()
                .pathParam("id", "1")
        .when()
                .get("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1"));
    }

    @Test
    @Sql({"/category/create_categories.sql", "/category/list_categories.sql"})
    public void shouldReturnListCategories() throws IOException {

        int amountHeaderRow = 1;
        int columnCategoryName = 1;
        int columnCategoryId = 0;
        String csvSeparator = ", ";

        var categoriesCsv = Files.readAllLines(new File( this.getClass()
                .getClassLoader()
                .getResource("category/categories.csv")
                .getPath()).toPath(), StandardCharsets.UTF_8);

        when()
                .get("/categories")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItems(categoriesCsv.stream()
                        .skip(amountHeaderRow)
                        .map(s -> s.split(csvSeparator)[columnCategoryName]).toArray()))
                .body("id", hasItems(categoriesCsv.stream()
                        .skip(amountHeaderRow)
                        .map(s -> s.split(csvSeparator)[columnCategoryId])
                        .map(Integer::parseInt).toArray()));
    }

    @Test
    @Sql({"/category/create_categories.sql", "/category/insert_categories.sql"})
    public void shouldDeleteCategoryById(){
        given()
                .pathParam("id", "1")
        .when()
                .delete("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Sql("/category/create_categories.sql")
    public void shouldThrowExceptionWhenDeleteCategoryThenIdNotFound(){
        given()
                .pathParam("id", "1")
        .when()
                .delete("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1"));
    }

    @Test
    @Sql({"/category/create_categories.sql", "/category/insert_categories.sql"})
    public void shouldUpdateCategoryById(){

        Category category = new Category("bike");
        category.setId(1);

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(category)
        .when()
                .put("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Sql("/category/create_categories.sql")
    public void shouldThrowExceptionWhenUpdateCategoryThenIdNotFound(){
        Category expected = new Category("bike");
        expected.setId(1);

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(expected)
        .when()
                .put("/categories/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(equalTo("Entity not found with id: 1"));
    }


}
