package com.entity;

import java.sql.Timestamp;

import com.base.BaseEntity;


public class Book extends BaseEntity {
	
	private String book_id;
	private String book_name;
	private String book_image;

	private String book_author;
	private Timestamp book_updateTime;
	private String book_count;

	private String book_type;
	private String book_chapter;


	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_image() {
		return book_image;
	}

	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public Timestamp getBook_updateTime() {
		return book_updateTime;
	}

	public void setBook_updateTime(Timestamp book_updateTime) {
		this.book_updateTime = book_updateTime;
	}

	public String getBook_count() {
		return book_count;
	}

	public void setBook_count(String book_count) {
		this.book_count = book_count;
	}

	public String getBook_type() {
		return book_type;
	}

	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}

	public String getBook_chapter() {
		return book_chapter;
	}

	public void setBook_chapter(String book_chapter) {
		this.book_chapter = book_chapter;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_name=" + book_name + ", book_image=" + book_image + ", book_author="
				+ book_author + ", book_updateTime=" + book_updateTime + ", book_count=" + book_count + ", book_type="
				+ book_type + ", book_chapter=" + book_chapter + "]";
	}

	
}
