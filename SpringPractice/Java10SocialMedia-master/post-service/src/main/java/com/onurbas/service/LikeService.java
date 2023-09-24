package com.onurbas.service;

import com.onurbas.repository.ILikeRepository;
import com.onurbas.repository.entity.Like;
import com.onurbas.utility.JwtTokenManager;
import com.onurbas.utility.ServiceManager;
import org.springframework.stereotype.Service;


@Service
public class LikeService extends ServiceManager<Like, String> {
    private final ILikeRepository likeRepository;
    private final JwtTokenManager jwtTokenManager;


    public LikeService(ILikeRepository likeRepository, JwtTokenManager jwtTokenManager) {
        super(likeRepository);
        this.likeRepository = likeRepository;
        this.jwtTokenManager = jwtTokenManager;
    }


}
