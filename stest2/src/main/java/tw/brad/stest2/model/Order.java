package tw.brad.stest2.model;

import java.util.LinkedList;
import java.util.List;

public class Order {
	private int orderId;
	private String orderDate;
	private List<OrderDetailV2> orderDetails = new LinkedList<>();
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public List<OrderDetailV2> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailV2> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	
	
}
