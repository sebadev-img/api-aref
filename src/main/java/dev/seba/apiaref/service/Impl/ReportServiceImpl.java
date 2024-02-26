package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.PostMetric;
import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.IReportService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportServiceImpl implements IReportService {

    private final CommentRestClient commentClient;

    public ReportServiceImpl(CommentRestClient commentClient){
        this.commentClient = commentClient;
    }
    @Override
    public PostReportResponseDto getTop10PostByComment() {
        List<Comment> comments = commentClient.findAll();
        Map<Integer,Integer> postMap = new HashMap<>();
        comments.forEach(comment -> {
            int postId = comment.postId();
            if(postMap.containsKey(postId)){
                int currentCommentCount = postMap.get(postId);
                postMap.put(postId,currentCommentCount+1);
            }else{
                postMap.put(postId,1);
            }
        });
        List<PostMetric> top = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : postMap.entrySet()){
            PostMetric postMetric = new PostMetric();
            postMetric.setPostId(entry.getKey());
            postMetric.setCommentCount(entry.getValue());
            top.add(postMetric);
        }
        top.sort(Comparator.comparingInt(PostMetric::getCommentCount).reversed());
        List<PostMetric> top10 = top.subList(0,Math.min(top.size(),10));
        PostReportResponseDto reportDto = new PostReportResponseDto();
        reportDto.setCount(top10.size());
        reportDto.setResults(top10);
        return reportDto;
    }
}
