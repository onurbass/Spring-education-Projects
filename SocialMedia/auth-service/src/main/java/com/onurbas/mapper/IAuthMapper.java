package com.onurbas.mapper;

import com.onurbas.dto.request.RegisterRequestDto;
import com.onurbas.dto.request.UserSaveRequestDto;
import com.onurbas.dto.response.LoginResponseDto;
import com.onurbas.dto.response.RegisterResponseDto;
import com.onurbas.rabbitmq.model.MailModel;
import com.onurbas.rabbitmq.model.RegisterModel;
import com.onurbas.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    LoginResponseDto toLoginResponseDto(final Auth auth);

    @Mapping(source = "id",target = "authId")
    UserSaveRequestDto toUserSaveRequestDto(final Auth auth);

    RegisterResponseDto toRegisterResponseDto(Auth auth);

    @Mapping(source = "id" ,target = "authId")
    RegisterModel  toRegisterModel(Auth auth);

    MailModel toMailModel(Auth auth);

}
