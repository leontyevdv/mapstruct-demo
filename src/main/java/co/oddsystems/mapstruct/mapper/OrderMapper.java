package co.oddsystems.mapstruct.mapper;

import co.oddsystems.mapstruct.CycleAvoidingMappingContext;
import co.oddsystems.mapstruct.dto.OrderDTO;
import co.oddsystems.mapstruct.entity.OrderEntity;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ProductMapper.class})
public interface OrderMapper {

    @Mapping(source = "products", target = "orderProducts")
    OrderDTO convert(OrderEntity entity, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    OrderEntity convert(OrderDTO dto, @Context CycleAvoidingMappingContext context);

    @Mapping(source = "products", target = "orderProducts")
    List<OrderDTO> convert(List<OrderEntity> entities);

}
