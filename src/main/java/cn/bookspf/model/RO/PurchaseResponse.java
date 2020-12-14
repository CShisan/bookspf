package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Purchase;

public class PurchaseResponse extends Response{
	private ArrayList<Purchase> purchases;

	public PurchaseResponse() {}

	public PurchaseResponse(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}

}
