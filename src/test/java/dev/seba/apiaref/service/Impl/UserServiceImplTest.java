package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.Address;
import dev.seba.apiaref.model.Company;
import dev.seba.apiaref.model.Geo;
import dev.seba.apiaref.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRestClient userClient;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void TestThatGetAllReturnsUsersResponseDto() {
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
        when(userClient.findAll()).thenReturn(data);
        UsersResponseDto usersDto = userService.getAll();
        assertEquals(2,usersDto.getCount());
    }

    @Test
    void TestThatGetUserByIdReturnsUser() {
        List<User> data = List.of(
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
                        )),
                new User(
                        3,
                        "test name",
                        "username3",
                        "email3",
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
                        ))

        );
        when(userClient.findAll()).thenReturn(data);
        User user = userService.getUserById(2);
        assertEquals(2,user.id());
    }

    @Test
    void TestThatGetUserByUsernameReturnsUser() {
        List<User> data = List.of(
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
                    )),
                new User(
                        3,
                        "test name",
                        "username3",
                        "email3",
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
                        ))

        );
        when(userClient.findAll()).thenReturn(data);
        User user = userService.getUserByUsername("username2");
        assertEquals("username2",user.username());
    }

    @Test
    void TestThatGetUserByEmailReturnsUser() {
        List<User> data = List.of(
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

                ),
                new User(
                        3,
                        "test name",
                        "username3",
                        "email3",
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
        when(userClient.findAll()).thenReturn(data);
        User user = userService.getUserByEmail("email2");
        assertEquals("email2",user.email());

    }

    @Test
    void TestThatGetUsersByCityReturnsUsersDto() {
        List<User> data = List.of(
                new User(
                        1,
                        "test name",
                        "username1",
                        "email1",
                        new Address(
                                "street",
                                "suite",
                                "city1",
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
                        "username1",
                        "email1",
                        new Address(
                                "street",
                                "suite",
                                "city2",
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
                        3,
                        "test name",
                        "username1",
                        "email1",
                        new Address(
                                "street",
                                "suite",
                                "city1",
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

                )
        );
        when(userClient.findAll()).thenReturn(data);
        UsersResponseDto usersDto = userService.getUsersByCity("city1");
        assertEquals(2,usersDto.getCount());

    }

    @Test
    void testThatSearchUserReturnsUser(){
        List<User> data = List.of(
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

                ));
        when(userClient.findAll()).thenReturn(data);
        when(userClient.findAll()).thenReturn(data);

        String username = "username2";
        String email = "email2";
        String emptyUsername = null;
        String emptyEmail = null;

        User user1 = userService.searchUser(username,email);
        assertEquals("username2",user1.username());
        assertEquals("email2",user1.email());

        User user2 = userService.searchUser(username,emptyEmail);
        assertEquals("username2",user2.username());

        User user3 = userService.searchUser(emptyUsername,email);
        assertEquals("email2",user3.email());

        User user4 = userService.searchUser(emptyUsername,emptyEmail);
        assertNull(user4);

    }
}