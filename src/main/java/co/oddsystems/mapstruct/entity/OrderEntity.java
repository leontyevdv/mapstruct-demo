package co.oddsystems.mapstruct.entity;

import java.util.List;
import lombok.Data;

@Data
public class OrderEntity {

    private long id;
    private List<BoughtProductEntity> products;
    private AddressEntity address;

    // TODO: Check circular deps

}
