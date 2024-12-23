package tw.brad.stest2.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad05")
public class Brad05 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping("/order/{orderId}")
	public void test1(@PathVariable int orderId) {
		String sql = "SELECT o.OrderID, o.OrderDate, od.UnitPrice, od.Quantity " + 
					"FROM orders o JOIN orderdetails od ON o.OrderID = od.OrderID " + 
					"WHERE o.OrderID = :orderId";
		
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		
		
		
		
		
		
		
		
		
	}
}
