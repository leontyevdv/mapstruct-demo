package co.oddsystems.mapstruct.mapper;

import co.oddsystems.mapstruct.CycleAvoidingMappingContext;
import co.oddsystems.mapstruct.dto.AddressDTO;
import co.oddsystems.mapstruct.entity.AddressEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(source = "address", target = "customerAddress")
    AddressDTO convert(AddressEntity entity, @Context CycleAvoidingMappingContext context);

}
