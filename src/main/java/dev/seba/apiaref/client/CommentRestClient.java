package dev.seba.apiaref.client;

import dev.seba.apiaref.dto.response.PostMetricsResponseDto;
import dev.seba.apiaref.model.Comment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CommentRestClient {
    private final RestClient restClient;

    public CommentRestClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    public List<Comment> findCommentsByPostId(int postId){
        return restClient.get()
                .uri("/comments?postId={postId}",postId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Comment>>() {});
    }


}
