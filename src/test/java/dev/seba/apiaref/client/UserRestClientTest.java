package dev.seba.apiaref.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.seba.apiaref.model.Address;
import dev.seba.apiaref.model.Company;
import dev.seba.apiaref.model.Geo;
import dev.seba.apiaref.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UserRestClient.class)
class UserRestClientTest {

    @Autowired
    MockRestServiceServer server;
    @Autowired
    UserRestClient userClient;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testThatFindAllReturnsListOfUsers() throws JsonProcessingException {
        List<User> data = List.of(
                new User(
                        1,
                        "test name",
                        "username1",
                        "email1",
                        new Address(
                                "street",
                                "suite",
                                "city",
                                "zipcode",
                                new Geo(1.0,1.0)
                        ),
                        "phone",
                        "website",
                        new Company(
                                "name",
                                "phrase",
                                "bs"
                        )

                ),
                new User(
                        2,
                        "test name",
                        "username2",
                        "email2",
                        new Address(
                                "street",
                                "suite",
                                "city",
                                "zipcode",
                                new Geo(2.0,2.0)
                        ),
                        "phone",
                        "website",
                        new Company(
                                "name",
                                "phrase",
                                "bs"
                        )

                )

        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/users"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        List<User> users = userClient.findAll();
        assertEquals(2,users.size());
    }

    @Test
    void findById() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByCity() {
    }
}