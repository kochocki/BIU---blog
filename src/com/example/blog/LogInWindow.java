package com.example.blog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LogInWindow extends Window {

	private static final long	serialVersionUID	= -5696627995814075601L;
	private TextField					tfLogin						= new TextField();
	private PasswordField			passwordField			= new PasswordField();
	private Button						logInButton				= new Button("Log in");

	public LogInWindow(BlogUI blogUI) {
		super("Log in");
		setModal(true);
		center();
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		setContent(subContent);
		subContent.addComponent(tfLogin);
		tfLogin.setCaption("Enter login:");
		subContent.addComponent(passwordField);
		passwordField.setCaption("Enter password:");
		subContent.addComponent(logInButton);
		logInButton.addClickListener(event -> {
			if (tfLogin.getValue().equals("admin") && passwordField.getValue().equals("admin")) {
				blogUI.savePostButton.setEnabled(true);
				blogUI.loginButton.setEnabled(false);
				close();
			}
			else {
				Notification.show("Login and password do not match", Notification.Type.ERROR_MESSAGE);
			}
		});
	}
}
