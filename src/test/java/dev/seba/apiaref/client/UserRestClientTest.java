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
    void testThatFindByIdReturnsUser() throws JsonProcessingException {
        User data = new User(
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

        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/users/2"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        User user = userClient.findById(2);
        assertEquals(2,user.id());
    }

    @Test
    void testThatFindByUsernameReturnsUser() throws JsonProcessingException {
        User data = new User(
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

        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/users?username=username2"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        User user = userClient.findByUsername("username2");
        assertEquals("username2",user.username());
    }

    @Test
    void testThatFindByEmailReturnsUser() throws JsonProcessingException {
        User data = new User(
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

        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/users?email=email2"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        User user = userClient.findByEmail("email2");
        assertEquals("email2",user.email());
    }

    @Test
    void findByCity() {
    }
}