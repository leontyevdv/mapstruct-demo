package co.oddsystems.mapstruct.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "order")
public class AddressDTO {

    private String customerAddress;
    private OrderDTO order;

}
