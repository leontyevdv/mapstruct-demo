package co.oddsystems.mapstruct.mapper;

import co.oddsystems.mapstruct.dto.BoughtProductDTO;
import co.oddsystems.mapstruct.entity.BoughtProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    BoughtProductDTO convert(BoughtProductEntity entity);

}
