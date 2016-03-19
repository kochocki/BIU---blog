package com.example.blog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NewPostForm {

	private Window window = new Window("New post");

	public NewPostForm() {
		showNewPostForm();
	}

	public Window getWindow() {
		return window;
	}

	private Window showNewPostForm() {
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		window.setContent(subContent);
		subContent.addComponent(new Label("Enter post title:"));
		subContent.addComponent(new TextField());
		subContent.addComponent(new Label("Enter post content:"));
		subContent.addComponent(new TextArea());
		subContent.addComponent(new Button("Send"));
		window.center();
		return window;
	}
}
