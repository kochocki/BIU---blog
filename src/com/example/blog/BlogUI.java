package com.example.blog;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@Theme("blog")
public class BlogUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = BlogUI.class)
	public static class Servlet extends VaadinServlet {}

	public List<Post> posts = new ArrayList<Post>();

	@Override
	protected void init(VaadinRequest request) {
		final AbsoluteLayout layout = new AbsoluteLayout();
		// layout.setMargin(true);
		setContent(layout);
		TextArea newPostTextArea = new TextArea();
		newPostTextArea.setWidth("50%");
		Button savePostButton = new Button("Add new post");
		savePostButton.addClickListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				Window subWindow = new Window("New post");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(subContent);
				subContent.addComponent(new Label("Meatball sub"));
				subContent.addComponent(new Button("Awlright"));
				subWindow.center();
				// posts.add(new Post(newPostTextArea.getValue()));
				// for (Post post : posts) {
				// System.out.print(post.getPostContent());
				// }
				addWindow(subWindow);
			}
		});
		layout.addComponent(newPostTextArea, "left: 25%; top: 40px;");
		layout.addComponent(savePostButton, "right: 0px; top: 0px;");
		// Button button = new Button("Click Me");
		// button.addClickListener(new Button.ClickListener() {
		// public void buttonClick(ClickEvent event) {
		// layout.addComponent(new Label("Thank you for clicking"));
		// }
		// });
		// layout.addComponent(button);
	}
}
