package co.oddsystems.mapstruct.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDTO {

    private long id;
    private List<BoughtProductDTO> orderProducts;
    private AddressDTO address;

}
