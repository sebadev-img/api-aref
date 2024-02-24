package dev.seba.apiaref.client;

import dev.seba.apiaref.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {

    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    public List<User> findAll(){
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {});
    }

    public User findById(int id){
        return restClient.get()
                .uri("/users/{id}",id)
                .retrieve()
                .body(User.class);
    }

    public User findByUsername(String username){
        return restClient.get()
                .uri("/users?username={username}",username)
                .retrieve()
                .body(User.class);
    }

    public User findByEmail(String email){
        return restClient.get()
                .uri("/users?email={email}",email)
                .retrieve()
                .body(User.class);
    }
}
