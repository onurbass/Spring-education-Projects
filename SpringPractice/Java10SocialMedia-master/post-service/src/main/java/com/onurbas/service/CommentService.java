package com.onurbas.service;

import com.onurbas.repository.ICommentRepository;
import com.onurbas.repository.entity.Comment;
import com.onurbas.utility.JwtTokenManager;
import com.onurbas.utility.ServiceManager;
import org.springframework.stereotype.Service;


@Service
public class CommentService extends ServiceManager<Comment, String> {
    private final ICommentRepository commentRepository;
    private final JwtTokenManager jwtTokenManager;


    public CommentService(ICommentRepository commentRepository, JwtTokenManager jwtTokenManager) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.jwtTokenManager = jwtTokenManager;
    }


}
