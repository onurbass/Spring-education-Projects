package com.bilgeadam.mapper;



import com.bilgeadam.dto.response.TahminResponeDto;
import com.bilgeadam.repository.entity.Tahmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITahminMapper {

    ITahminMapper INSTANCE= Mappers.getMapper(ITahminMapper.class);


    TahminResponeDto toTahminResponeDto(Tahmin tahmin);
}
