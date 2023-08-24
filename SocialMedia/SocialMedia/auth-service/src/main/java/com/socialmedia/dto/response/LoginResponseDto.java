package com.socialmedia.dto.response;

import com.socialmedia.repository.enums.ERole;
import com.socialmedia.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private Long id;
    private  String username;
    private  String email;
    private ERole role;
    private EStatus status;
    private Long createDate;
}
