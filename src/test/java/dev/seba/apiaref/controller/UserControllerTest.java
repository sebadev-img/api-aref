package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.Address;
import dev.seba.apiaref.model.Company;
import dev.seba.apiaref.model.Geo;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.Impl.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void TestThatGetAllReturnsUsersDto() throws Exception {
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
        UsersResponseDto usersDto = new UsersResponseDto();
        usersDto.setCount(data.size());
        usersDto.setResults(data);
        when(userService.getAll()).thenReturn(usersDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(data.size())));
    }

    @Test
    void TestThatGetUserByIdReturnUser() throws Exception {
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
        when(userService.getUserById(2)).thenReturn(data);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/2")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",CoreMatchers.is(data.id())));
    }

    @Test
    void TestThatSearchUserReturnsByUsername() throws Exception {
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
        when(userService.searchUser("username2",null)).thenReturn(data);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/search?username=username2")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username",CoreMatchers.is(data.username())));

    }

    @Test
    void TestThatSearchUserReturnsByEmail() throws Exception {
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
        when(userService.searchUser(null,"email2")).thenReturn(data);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/search?email=email2")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",CoreMatchers.is(data.email())));

    }

    @Test
    void TestThatGetUsersByCityReturnUsersDto() throws Exception {
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

                ));
        UsersResponseDto usersDto = new UsersResponseDto();
        usersDto.setCount(data.size());
        usersDto.setResults(data);
        when(userService.getUsersByCity("city")).thenReturn(usersDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/cities/city")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count",CoreMatchers.is(data.size())));
    }
}