package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.SehirRequestDto;
import com.bilgeadam.repository.entity.Sehir;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISehirMapper {

    ISehirMapper INSTANCE= Mappers.getMapper(ISehirMapper.class);
   Sehir toSehir(SehirRequestDto dto);
}
