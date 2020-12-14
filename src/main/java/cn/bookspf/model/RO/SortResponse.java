package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Sort;

public class SortResponse extends Response{
	private ArrayList<Sort> sorts;
	
	public SortResponse() {}
	public SortResponse(ArrayList<Sort> sorts) {
		this.sorts=sorts;
	}

	public ArrayList<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(ArrayList<Sort> sorts) {
		this.sorts = sorts;
	}
}
