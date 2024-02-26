package dev.seba.apiaref.client;

import dev.seba.apiaref.exception.PostNotFoundException;
import dev.seba.apiaref.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
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
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new PostNotFoundException("User Not Found");})
                .body(User.class);
    }

    public List<User> findByUsername(String username){
        return restClient.get()
                .uri("/users?username={username}",username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new PostNotFoundException("User Not Found");})
                .body(new ParameterizedTypeReference<List<User>>() {});
    }

    public List<User> findByEmail(String email){
        return restClient.get()
                .uri("/users?email={email}",email)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new PostNotFoundException("User Not Found");})
                .body(new ParameterizedTypeReference<List<User>>() {});
    }

    public List<User> findByCity(String city){
        return restClient.get()
                .uri("/users?address.city={city}",city)
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {});
    }
}
