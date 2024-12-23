package tw.brad.stest2.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tw.brad.stest2.model.OrderDetail;

@Component
public class OrderDetailRowMapper  implements RowMapper<OrderDetail>{

	@Autowired
	private OrderDetail orderDetail;
	
	@Override
	public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		orderDetail.setOrderId(rs.getInt("OrderID"));
		orderDetail.setOrderDate(rs.getString("OrderDate"));
		orderDetail.setUnitPrice(rs.getDouble("UnitPrice"));
		orderDetail.setQty(rs.getInt("Quantity"));
		orderDetail.setProductName(rs.getString("ProductName"));
		
		return orderDetail;
	}

	
	
}
