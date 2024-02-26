package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private final CommentRestClient commentClient;

    public CommentServiceImpl(CommentRestClient commentClient){
        this.commentClient = commentClient;
    }
    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = commentClient.findAll();
        List<Comment> postComments = comments.stream().filter(comment ->
                comment.postId()==postId
        ).toList();
        return postComments;
    }
}
