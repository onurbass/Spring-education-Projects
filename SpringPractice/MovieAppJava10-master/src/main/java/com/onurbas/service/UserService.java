package com.onurbas.service;

import com.onurbas.dto.request.LoginRequestDto;
import com.onurbas.dto.request.RegisterRequestDto;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.repository.IUserRepository;
import com.onurbas.repository.entity.User;
import com.onurbas.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;


    public User createUser(User user) {

        return userRepository.save(user);
    }

    public User register(RegisterRequestDto dto) {
//        User user =User.builder().phone(dto.getPhone()).name(dto.getName()).surName(dto.getSurName())
//                .email(dto.getEmail()).password(dto.getPassword()).build();

        User user = IUserMapper.INSTANCE.toUser(dto);
        return userRepository.save(user);
    }
    public User findById(Long id){
        Optional<User> user =userRepository.findById(id);
        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
        return user.get();
    }


    public UserResponseDto login(LoginRequestDto dto) {
        Optional<User> user = userRepository.findByEmailAndAndPassword(dto.getEmail(), dto.getPassword());
        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
//        return UserResponseDto.builder()
//                .id(user.get().getId())
//                .userType(user.get().getUserType())
//                .email(user.get().getEmail())
//                .name(user.get().getName())
//                .surName(user.get().getSurName())
//                .phone(user.get().getPhone())
//                .build();

        return IUserMapper.INSTANCE.toUserResponseDto(user.get());
    }
    public List<User> findAllByOrderByName(){
        return userRepository.findAllByOrderByName();
    }
}
