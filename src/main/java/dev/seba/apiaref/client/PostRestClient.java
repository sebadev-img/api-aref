package dev.seba.apiaref.client;

import dev.seba.apiaref.exception.PostNotFoundException;
import dev.seba.apiaref.model.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class PostRestClient {

    private final RestClient restClient;

    public PostRestClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }
    public List<Post> findAll(){
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    public Post findById(int id){
        return restClient.get()
                .uri("/posts/{id}",id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new PostNotFoundException("Post Not Found");})
                .body(Post.class);
    }

    public List<Post> findByUserId(int userId){
        return restClient.get()
                .uri("/posts?userId={userId}",userId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new PostNotFoundException("Post Not Found");})
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }
}
