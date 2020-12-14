package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Shopcar;

public class ShopcarResponse extends Response{
	private ArrayList<Shopcar> shopcars;
	
	public ShopcarResponse() {}
	public ShopcarResponse(ArrayList<Shopcar> shopcars) {
		this.shopcars=shopcars;
	}
	public ArrayList<Shopcar> getShopcars() {
		return shopcars;
	}
	public void setShopcars(ArrayList<Shopcar> shopcars) {
		this.shopcars = shopcars;
	}

}
