package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.PostMetric;
import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.IReportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportService implements IReportService {

    private final CommentRestClient commentClient;
    private final PostRestClient postClient;

    public ReportService(CommentRestClient commentClient, PostRestClient postClient){
        this.commentClient = commentClient;
        this.postClient = postClient;
    }
    @Override
    public PostReportResponseDto getTop10PostByComment() {
        List<PostMetric> top = new ArrayList<>();
        List<Post> posts = postClient.findAll();
        posts.forEach(post -> {
            List<Comment> comments = commentClient.findCommentsByPostId(post.id());
            PostMetric postMetric = new PostMetric();
            postMetric.setPostId(post.id());
            postMetric.setCommentCount(comments.size());
            top.add(postMetric);
        });
        top.sort(Comparator.comparingInt(PostMetric::getCommentCount).reversed());
        List<PostMetric> top10 = top.subList(0,Math.min(top.size(),10));
        PostReportResponseDto postReport = new PostReportResponseDto();
        postReport.setCount(top10.size());
        postReport.setResults(top10);
        return postReport;
    }
}
