package co.oddsystems.mapstruct.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "order")
public class AddressEntity {

    private String address;
    private OrderEntity order;

}
