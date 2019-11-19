package com.udelphi.rest_api.integration;

import com.udelphi.rest_api.model.Role;
import com.udelphi.rest_api.model.User;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    @Sql("/create_table_user.sql")
    public void shouldReturnUserWithId(){
        User user = new User("John", "smth@gmail.com", Set.of(new Role("admin")));
    }


}
