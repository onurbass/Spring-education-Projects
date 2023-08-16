package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.KayitRequestDto;
import com.bilgeadam.dto.response.KayitResponseDto;
import com.bilgeadam.repository.entity.Kullanici;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IKullaniciMapper {


    IKullaniciMapper INSTANCE= Mappers.getMapper(IKullaniciMapper.class);

    Kullanici toKullanici(KayitRequestDto dto);
    KayitResponseDto toKayitResponseDto(Kullanici kullanici);
}
