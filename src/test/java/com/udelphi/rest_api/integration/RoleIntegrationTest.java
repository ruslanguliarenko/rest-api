package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Role;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RoleIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }


    @Test
    @Sql("/create_table_roles.sql")
    public void shouldReturnRoleWithId(){

        Role role = new Role("admin");
        given()
                .contentType(ContentType.JSON)
                .body(role)
        .when()
                .post("/roles")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("admin"))
                .body("id", notNullValue());
    }

    @Test
    @Sql({"/create_table_roles.sql", "/insert_roles.sql"})
    public void shouldReturnRoleById(){
        given()
                .pathParam("id", "1")
        .when()
                .get("/roles/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("admin"))
                .body("id", equalTo(1));
    }


    @Test
    @Sql({"/create_table_roles.sql", "/insert_roles.sql"})
    public void shouldReturnAllRoles() throws IOException {

        String csvSplitter = ", ";
        int nameColumn = 1, idColumn = 0;
        var roleCsv = Files.readAllLines(new File(this.getClass()
                                                    .getClassLoader()
                                                    .getResource("roles.csv").getPath()).toPath());
        when()
                .get("/roles")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItems(roleCsv.stream()
                                                    .skip(1)
                                                    .map(s -> s.split(csvSplitter)[nameColumn])
                                                    .toArray()))
                .body("id", hasItems(roleCsv.stream()
                                                    .skip(1)
                                                    .map(s -> s.split(csvSplitter)[idColumn])
                                                    .map(Integer::valueOf)
                                                    .toArray()));
    }

    @Test
    @Sql({"/create_table_roles.sql", "/insert_roles.sql"})
    public void shouldUpdateRoleById(){
        Role role = new Role("update");
        role.setId(1);

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(role)
        .when()
                .put("/roles/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    @Sql({"/create_table_roles.sql", "/insert_roles.sql"})
    public void shouldDeleteRoleById(){
        given()
                .pathParam("id", "1")
        .when()
                .delete("/roles/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());


    }

}
