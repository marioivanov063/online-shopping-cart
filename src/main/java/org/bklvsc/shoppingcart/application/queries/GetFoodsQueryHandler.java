package org.bklvsc.shoppingcart.application.queries;

import java.util.List;
import java.util.stream.Collectors;

import org.bklvsc.shoppingcart.domain.port.in.queries.GetFoodsQuery;
import org.bklvsc.shoppingcart.domain.port.in.queries.QueryHandler;
import org.bklvsc.shoppingcart.domain.port.in.queries.dtos.FoodDto;
import org.bklvsc.shoppingcart.domain.port.out.read.FoodReadRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetFoodsQueryHandler implements QueryHandler<GetFoodsQuery, List<FoodDto>>{
	private JdbcTemplate template;
	
	public GetFoodsQueryHandler(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public List<FoodDto> handle(GetFoodsQuery query) {
		String sql = "SELECT * FROM food";
		List<FoodDto> foods = this.template.query(
				sql,
				(rs, rn) -> new FoodDto(
								rs.getString(1), 
						 		rs.getDouble(2),
						 		rs.getString(3)
						 	)
		);
		return foods;		
	}

}
