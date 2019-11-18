package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.model.Product;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Set;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductIntegrationTest {
    @LocalServerPort
    private int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    @Sql("/product/create_products.sql")
    public void shouldReturnProductWithId(){
        Product product = new Product("Giant", 4000.20, Set.of(new Category("bike")));
        given()
                .contentType(ContentType.JSON)
                .body(product)
        .when()
                .post("/products")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("schema/product_schema.json").using(settings().with().checkedValidation(false)))
                .body("id", notNullValue())
                .body("model", equalTo("Giant"))
                .body("price", equalTo(4000.20f))
                .body("categories.name", hasItem("bike"));
    }


    @Test
    @Sql({"/product/create_products.sql", "/product/add_product.sql"})
    public void shouldGetProductById(){
        given()
                .pathParam("id", "1")
        .when()
                .get("/products/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schema/product_schema.json").using(settings().with().checkedValidation(false)))
                .body("id", equalTo(1))
                .body("model", equalTo("Giant"))
                .body("price", equalTo(4000.20f));
    }

    @Test
    @Sql({"/product/create_products.sql", "/product/add_products.sql"})
    public void shouldReturnListProducts(){
        given()
        .when()
                .get("/products")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("model", hasItems(List.of("Giant", "BMW", "Audi")))
                .body("price", hasItems(4000.20f, 53000.0f, 41000f))
                .root( "find {it.type.name == '%s'}.categories")
                .body("name", withArgs("BMW"), is("car"))
                .body("name", withArgs("Giant"), is("bike"))
                .body("name", withArgs("Audi"), is("car"));

    }











}
