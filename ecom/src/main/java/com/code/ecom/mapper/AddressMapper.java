package com.code.ecom.mapper;

import com.code.ecom.dto.AddressDto;
import com.code.ecom.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // DTO → Entity conversion (Create new Address from DTO)
    @Mapping(target = "id", ignore = true)
    Address toAddress(AddressDto addressDto);

    // Entity → DTO conversion (Create DTO from Address)
    AddressDto toAddressDto(Address address);

    // In-place update of existing Address entity from DTO
    @Mapping(target = "id", ignore = true)
    void updateAddressFromAddressDto(AddressDto addressDto, @MappingTarget Address address);
}
