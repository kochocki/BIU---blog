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
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

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
		Panel newPostTextArea = new Panel("Title");
		newPostTextArea.setContent(new Label("Test label"));
		newPostTextArea.setWidth("50%");
		Button savePostButton = new Button("Add new post");
		savePostButton.addClickListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				NewPostForm newPostForm = new NewPostForm();
				addWindow(newPostForm.getWindow());
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
