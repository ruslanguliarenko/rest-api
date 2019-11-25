package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Role;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RoleIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
    }


    @Test
    @Sql("/table/create_roles.sql")
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
    @Sql({"/table/create_roles.sql", "/insert/insert_roles.sql"})
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
    @Sql({"/table/create_roles.sql", "/insert/insert_roles.sql"})
    public void shouldReturnAllRoles() throws IOException {

        String csvSplitter = ", ";
        int nameColumn = 1, idColumn = 0;
        var roleCsv = Files.readAllLines(new File(this.getClass()
                                                    .getClassLoader()
                                                    .getResource("csv/roles.csv").getPath()).toPath());
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
    @Sql({"/table/create_roles.sql", "/insert/insert_roles.sql"})
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
    @Sql({"/table/create_roles.sql", "/insert/insert_roles.sql"})
    public void shouldDeleteRoleById(){
        given()
                .pathParam("id", "1")
        .when()
                .delete("/roles/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        
    }

}
