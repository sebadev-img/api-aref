package dev.seba.apiaref.client;

import dev.seba.apiaref.model.Post;
import org.springframework.core.ParameterizedTypeReference;
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
                .body(Post.class);
    }
}
