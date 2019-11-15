package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Category;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.equalTo;
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
    @Sql("/create_categories.sql")
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
    @Sql({"/create_categories.sql", "/insert_categories.sql"})
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


}
