package dev.seba.apiaref.service;

import dev.seba.apiaref.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getCommentsByPostId(int postId);
}
