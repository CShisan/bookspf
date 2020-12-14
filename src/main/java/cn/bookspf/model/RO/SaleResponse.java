package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Sale;

public class SaleResponse extends Response{
	private ArrayList<Sale> sales;
	
	public SaleResponse() {}
	public SaleResponse(ArrayList<Sale> sales) {
		this.sales=sales;
	}
	public ArrayList<Sale> getSales() {
		return sales;
	}
	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}
	
	
}
