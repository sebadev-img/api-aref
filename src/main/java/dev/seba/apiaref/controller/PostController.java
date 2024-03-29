package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.IPostService;
import dev.seba.apiaref.service.Impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final IPostService postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PostsResponseDto> getAllPosts(){
        PostsResponseDto response = postService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id){
        Post response = postService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PostsResponseDto> searchPosts(@RequestParam(required = false) Integer userId, @RequestParam(required = false) String bodyText){
        PostsResponseDto response = postService.searchPosts(userId,bodyText);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
