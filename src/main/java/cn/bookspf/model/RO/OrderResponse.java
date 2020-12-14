package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Order;

public class OrderResponse extends Response {
	private ArrayList<Order> orders;

	public OrderResponse() {}

	public OrderResponse(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
