package com.udelphi.rest_api.integration;

import java.util.Set;
import com.udelphi.rest_api.model.Role;
import com.udelphi.rest_api.model.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    @Sql("/create_table_user.sql")
    public void shouldReturnUserWithId(){
        User user = new User("John", "smth@gmail.com", Set.of(new Role("admin")));
    }


}
