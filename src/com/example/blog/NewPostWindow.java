package com.example.blog;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NewPostWindow extends Window {

	private static final long serialVersionUID = 532448796258115637L;

	public interface INewPostWindow {

		public Post getPost();
	}

	private TextField	tfPostTitle		= new TextField();
	private TextArea	taPostContent	= new TextArea();
	private Button		sendButton		= new Button("Send");

	public NewPostWindow(BlogUI blogUI) {
		super("New post");
		setModal(true);
		center();
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		setContent(subContent);
		subContent.addComponent(tfPostTitle);
		tfPostTitle.setCaption("Enter post title:");
		subContent.addComponent(taPostContent);
		taPostContent.setCaption("Enter post content:");
		subContent.addComponent(sendButton);
		sendButton.addClickListener(event -> {
			if (validate()) {
				blogUI.addNewPost(new Post(tfPostTitle.getValue(), taPostContent.getValue()));
				close();
			}
		});
		// validation
		tfPostTitle.setImmediate(true);
		tfPostTitle.addValidator(new BeanValidator(Post.class, "title"));
		taPostContent.setImmediate(true);
		taPostContent.addValidator(new BeanValidator(Post.class, "content"));
	}

	private boolean validate() {
		return tfPostTitle.isValid() && taPostContent.isValid();
	}
}
