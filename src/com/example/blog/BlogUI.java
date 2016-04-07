package com.example.blog;

import javax.servlet.annotation.WebServlet;
import com.example.blog.Broadcaster.BroadcastListener;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("blog")
@Push
public class BlogUI extends UI implements BroadcastListener {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BlogUI.class)
	public static class Servlet extends VaadinServlet {}

	private VerticalLayout	postsLayout	= new VerticalLayout();
	private AbsoluteLayout	mainLayout	= new AbsoluteLayout();

	@Override
	protected void init(VaadinRequest request) {
		Button savePostButton = new Button("Add new post");
		mainLayout.addComponent(savePostButton, "right: 0px; top: 0px;");
		setContent(mainLayout);
		mainLayout.addComponent(postsLayout, "left: 25%; top: 30px");
		savePostButton.addClickListener(event -> {
			NewPostWindow w = new NewPostWindow(this);
			BlogUI.this.addWindow(w);
		});
		Broadcaster.register(this);
	}

	@Override
	protected void finalize() throws Throwable {
		Broadcaster.unregister(this);
		super.finalize();
	}

	public void addNewPost(Post post) {
		PostComponent postComp = new PostComponent();
		ObjectProperty<Post> prop = new ObjectProperty<Post>(post);
		postComp.setPropertyDataSource(prop);
		postsLayout.addComponent(postComp);
	}

	@Override
	public void receiveBroadcast(Post message) {
		addNewPost(message);
	}
}
