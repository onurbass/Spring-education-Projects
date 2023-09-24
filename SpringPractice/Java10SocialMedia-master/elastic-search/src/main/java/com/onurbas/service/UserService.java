package com.onurbas.service;
import com.onurbas.repository.IUserRepository;
import com.onurbas.repository.entity.UserProfile;
import com.onurbas.utility.ServiceManager;
import org.springframework.stereotype.Service;
@Service
public class UserService extends ServiceManager<UserProfile, String> {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


}
