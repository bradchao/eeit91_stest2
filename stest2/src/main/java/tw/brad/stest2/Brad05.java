package tw.brad.stest2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.stest2.mapper.OrderDetailRowMapper;
import tw.brad.stest2.model.OrderDetail;

@RestController
@RequestMapping("/brad05")
public class Brad05 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private OrderDetailRowMapper mapper;
	
	@RequestMapping("/order/{orderId}")
	public List<OrderDetail> test1(@PathVariable int orderId) {
		String sql = "SELECT o.OrderID, o.OrderDate, od.UnitPrice, od.Quantity, p.ProductName FROM orders o " + 
					"JOIN orderdetails od ON o.OrderID = od.OrderID " + 
					"JOIN products p ON p.ProductID = od.ProductID " + 
					"WHERE o.OrderID = :orderId";
		
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		
		List<OrderDetail> list = namedParameterJdbcTemplate.query(sql, params, mapper);
		
		return list;
	}
}
