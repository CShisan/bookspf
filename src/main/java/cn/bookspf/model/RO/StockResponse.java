package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Stock;

public class StockResponse extends Response{
	private ArrayList<Stock> stocks;
	
	public StockResponse() {}
	public StockResponse(ArrayList<Stock> stocks) {
		this.stocks=stocks;
	}
	public ArrayList<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	
}
