package com.example.blog;

import java.io.Serializable;
import javax.validation.constraints.Size;

public class Post implements Serializable {

	private static final long	serialVersionUID	= 3411852205079204243L;
	@Size(min = 5, max = 30)
	private String						title;
	@Size(min = 10, max = 1000)
	private String						content;

	public Post(String title, String postContent) {
		setTitle(title);
		setContent(postContent);
	}

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
