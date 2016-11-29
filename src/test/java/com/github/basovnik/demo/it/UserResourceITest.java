package com.github.basovnik.demo.it;

import com.github.basovnik.demo.api.UserResource;
import com.github.basovnik.demo.domain.model.User;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceITest {

    @LocalServerPort
    private int port;

    @Before
    public void before() {
        RestAssured.port = port;
        RestAssured.basePath = "/services";
    }

    @Test
    public void test() throws InterruptedException {
        // Create new user
        String id = given().contentType(APPLICATION_XML)
                .body(new User("Martin", "Basovnik"))
                .when().post(UserResource.PATH)
                .then().statusCode(Status.CREATED.getStatusCode())
                .extract().body().path("user.id");

        final String userResource = UserResource.PATH + "/" + id;

        // Get user
        given().accept(APPLICATION_XML)
                .when().get(userResource)
                .then().statusCode(Status.OK.getStatusCode());

        // Remove user
        given().when().delete(userResource)
                .then().statusCode(Status.NO_CONTENT.getStatusCode());
    }
}
