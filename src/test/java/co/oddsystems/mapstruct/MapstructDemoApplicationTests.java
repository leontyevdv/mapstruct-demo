package co.oddsystems.mapstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.oddsystems.mapstruct.dto.OrderDTO;
import co.oddsystems.mapstruct.entity.AddressEntity;
import co.oddsystems.mapstruct.entity.BoughtProductEntity;
import co.oddsystems.mapstruct.entity.OrderEntity;
import co.oddsystems.mapstruct.mapper.OrderMapper;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

@SpringBootTest
class MapstructDemoApplicationTests {

	@Autowired
	private OrderMapper orderMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testOrderMapperExists() {
		assertNotNull(orderMapper);
	}

	@Test
	void testOrderMapperIsAbleToConvertEntity() {
		// given
		BoughtProductEntity boughtProductEntity1 = new BoughtProductEntity();
		boughtProductEntity1.setId(1L);
		boughtProductEntity1.setQuantity(1);

		BoughtProductEntity boughtProductEntity2 = new BoughtProductEntity();
		boughtProductEntity2.setId(2L);
		boughtProductEntity2.setQuantity(2);

		List<BoughtProductEntity> productEntities = Arrays.asList(boughtProductEntity1, boughtProductEntity2);

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddress("Some address");

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setAddress(addressEntity);
		orderEntity.setProducts(productEntities);

		// when
		OrderDTO orderDTO = orderMapper.convert(orderEntity, new CycleAvoidingMappingContext());

		assertThat(orderDTO.getId(), Matchers.is(orderEntity.getId()));
		assertThat(orderDTO.getOrderProducts().size(), Matchers.is(2));

		assertThat(orderDTO.getOrderProducts().get(0).getId(), Matchers.is(1L));
		assertThat(orderDTO.getOrderProducts().get(0).getQuantity(), Matchers.is(1));

		assertThat(orderDTO.getOrderProducts().get(1).getId(), Matchers.is(2L));
		assertThat(orderDTO.getOrderProducts().get(1).getQuantity(), Matchers.is(2));

		assertThat(orderDTO.getAddress().getCustomerAddress(), Matchers.is(addressEntity.getAddress()));
	}

	@Test
	void testOrderMapperIsAbleToConvertEntityWithCircularDependency() {
		// given
		BoughtProductEntity boughtProductEntity1 = new BoughtProductEntity();
		boughtProductEntity1.setId(1L);
		boughtProductEntity1.setQuantity(1);

		BoughtProductEntity boughtProductEntity2 = new BoughtProductEntity();
		boughtProductEntity2.setId(2L);
		boughtProductEntity2.setQuantity(2);

		List<BoughtProductEntity> productEntities = Arrays.asList(boughtProductEntity1, boughtProductEntity2);

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddress("Some address");

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setAddress(addressEntity);
		orderEntity.setProducts(productEntities);

		// Circular dependency
		orderEntity.setAddress(addressEntity);
		addressEntity.setOrder(orderEntity);

		// when
		OrderDTO orderDTO = orderMapper.convert(orderEntity, new CycleAvoidingMappingContext());

		assertThat(orderDTO.getId(), Matchers.is(orderEntity.getId()));
		assertThat(orderDTO.getOrderProducts().size(), Matchers.is(2));

		assertThat(orderDTO.getOrderProducts().get(0).getId(), Matchers.is(1L));
		assertThat(orderDTO.getOrderProducts().get(0).getQuantity(), Matchers.is(1));

		assertThat(orderDTO.getOrderProducts().get(1).getId(), Matchers.is(2L));
		assertThat(orderDTO.getOrderProducts().get(1).getQuantity(), Matchers.is(2));

		assertThat(orderDTO.getAddress().getCustomerAddress(), Matchers.is(addressEntity.getAddress()));

		assertThat(orderDTO.getAddress(), Matchers.notNullValue());
		assertThat(orderDTO.getAddress().getOrder(), Matchers.sameInstance(orderDTO));
	}

}
