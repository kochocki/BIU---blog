package com.example.blog;

import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LogInWindow extends Window {

	private TextField			tfLogin				= new TextField();
	private PasswordField	passwordField	= new PasswordField();
	private Button				logInButton		= new Button("Log in");

	public LogInWindow(BlogUI blogUI) {
		super("New post");
		setModal(true);
		center();
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		setContent(subContent);
		subContent.addComponent(tfLogin);
		tfLogin.setCaption("Enter post title:");
		subContent.addComponent(passwordField);
		passwordField.setCaption("Enter post content:");
		subContent.addComponent(logInButton);
		logInButton.addClickListener(event -> {
			if (tfLogin.getValue().equals("admin") && passwordField.getValue().equals("admin")) {
				blogUI.savePostButton.setEnabled(true);
				close();
			}
		});
		// validation
		// tfPostTitle.setImmediate(true);
		// tfPostTitle.addValidator(new BeanValidator(Post.class, "title"));
		// taPostContent.setImmediate(true);
		// taPostContent.addValidator(new BeanValidator(Post.class, "content"));
	}
}
