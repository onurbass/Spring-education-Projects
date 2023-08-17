package com.onurbas.mapper;

import com.onurbas.dto.request.RegisterRequestDTO;
import com.onurbas.dto.response.RegisterResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMusteriMapper {
  IMusteriMapper INSTANCE = Mappers.getMapper(IMusteriMapper.class);

  /*
  Bundan sonrasında dönüşüm yapmak istediğiniz sınıflarla iligli metodları yazın.
   */
  RegisterResponseDTO fromMusteri(final Musteri musteri);

  Musteri fromSaveRequestDto(final RegisterRequestDTO dto);
}
