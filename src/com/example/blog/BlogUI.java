package com.example.blog;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("blog")
public class BlogUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BlogUI.class)
	public static class Servlet extends VaadinServlet {}

	private VerticalLayout layout = new VerticalLayout();

	@Override
	protected void init(VaadinRequest request) {
		// TextField postTitleTextField = new TextField();
		// TextArea postContentTextArea = new TextArea();
		Button savePostButton = new Button("Add new post");
		// layout.addComponent(postTitleTextField);
		// layout.addComponent(postContentTextArea);
		layout.addComponent(savePostButton);
		setContent(layout);
		//
		savePostButton.addClickListener(event -> {
			NewPostWindow w = new NewPostWindow(this);
			BlogUI.this.addWindow(w);
		});
	}

	public void addNewPost(Post post) {
		PostComponent postComp = new PostComponent();
		ObjectProperty<Post> prop = new ObjectProperty<Post>(post);
		postComp.setPropertyDataSource(prop);
		layout.addComponent(postComp);
	}
}
