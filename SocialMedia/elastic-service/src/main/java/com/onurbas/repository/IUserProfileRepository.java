package com.onurbas.repository;

import com.onurbas.repository.entity.UserProfile;
import com.onurbas.repository.enums.EStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,String> {
    Optional<UserProfile> findByUsername(String username);
    List<UserProfile> findAllByEmailContainingIgnoreCase(String value);
    List<UserProfile> findAllByStatus(EStatus status);
    List<UserProfile> findAllByStatusOrAddress(EStatus status,String address);

}
