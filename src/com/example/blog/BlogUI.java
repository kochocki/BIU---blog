package com.example.blog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	private VerticalLayout	postsLayout			= new VerticalLayout();
	private AbsoluteLayout	mainLayout			= new AbsoluteLayout();
	public Button						loginButton			= new Button("Log in");
	public Button						savePostButton	= new Button("Add new post");

	@Override
	protected void init(VaadinRequest request) {
		Broadcaster.register(this);
		savePostButton.setEnabled(false);
		mainLayout.addComponent(savePostButton, "right: 0px; top: 0px;");
		mainLayout.addComponent(loginButton);
		setContent(mainLayout);
		mainLayout.addComponent(postsLayout, "left: 25%; top: 30px");
		getPosts();
		loginButton.addClickListener(event -> {
			LogInWindow w = new LogInWindow(this);
			BlogUI.this.addWindow(w);
		});
		savePostButton.addClickListener(event -> {
			NewPostWindow w = new NewPostWindow(this);
			BlogUI.this.addWindow(w);
		});
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

	private void getPosts() {
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\posts.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineParts = line.split(",");
				addNewPost(new Post(lineParts[0], lineParts[1]));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
