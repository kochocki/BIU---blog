package com.example.blog;

public class Post {

	Post(String postContent) {
		setPostContent(postContent);
	}

	private String postContent;

	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
}
