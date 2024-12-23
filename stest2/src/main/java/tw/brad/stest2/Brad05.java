package tw.brad.stest2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.stest2.mapper.OrderDetailRowMapper;
import tw.brad.stest2.model.Order;
import tw.brad.stest2.model.OrderDetail;
import tw.brad.stest2.model.OrderDetailV2;

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
	
	@GetMapping(value = {"/orders", "/orders/{orderId}"})
	public List<Order> test2(@PathVariable(required = false) Integer orderId ) {
		String sql = "SELECT o.OrderID, o.OrderDate, od.UnitPrice, od.Quantity, p.ProductName FROM orders o " + 
				"JOIN orderdetails od ON o.OrderID = od.OrderID " + 
				"JOIN products p ON p.ProductID = od.ProductID"; 
	
		Map<String, Object> params = new HashMap<>();
		
		if (orderId != null) {
			sql += " WHERE o.OrderID = :orderId";
			params.put("orderId", orderId);
		}
		
		List<Map<String,Object>> rows = namedParameterJdbcTemplate.queryForList(sql, params);
		
		
		Map<Integer, Order> orderMap = new HashMap<>();
		
		for (Map<String,Object> row : rows) {
			int oid = (Integer)row.get("OrderID");
			
			Order order = orderMap.get(oid);
			if (order == null) {
				order = new Order();
				order.setOrderId(oid);
				order.setOrderDate(row.get("OrderDate").toString());
				orderMap.put(oid, order);
			}
			
			OrderDetailV2 detail = new OrderDetailV2();
			detail.setUnitPrice(((BigDecimal)row.get("UnitPrice")).doubleValue());
			detail.setQty((Integer)row.get("Quantity"));
			detail.setProductName((String)row.get("ProductName"));
			
			order.getOrderDetails().add(detail);
		}
		
		return new LinkedList<>(orderMap.values()); 
		
	}
	
	
	
}
