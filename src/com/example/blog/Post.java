package com.example.blog;

public class Post {

	Post(String postContent) {
		setContent(postContent);
	}

	private String	content;
	private String	title;

	public String getContent() {
		return this.content;
	}

	public void setContent(String postContent) {
		this.content = postContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
