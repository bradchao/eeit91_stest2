package tw.brad.stest2.model;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailV2 {
	private double unitPrice;
	private int qty;
	private double total;
	private String productName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
		total = unitPrice * qty;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
		total = unitPrice * qty;
	}
	
	
	
	
}
