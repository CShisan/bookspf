package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.Book;

public class BookResponse extends Response{
	private ArrayList<Book> books;

	
	public BookResponse() {}
	public BookResponse(ArrayList<Book> books) {
		this.books=books;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	
}
