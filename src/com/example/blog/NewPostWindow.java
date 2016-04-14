package com.example.blog;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NewPostWindow extends Window {

	private static final long	serialVersionUID	= 532448796258115637L;
	private TextField					tfPostTitle				= new TextField();
	private RichTextArea			taPostContent			= new RichTextArea();
	private Button						sendButton				= new Button("Send");

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
				Broadcaster.broadcast(new Post(tfPostTitle.getValue(), taPostContent.getValue()));
				try {
					PostsBase.add(new Post(tfPostTitle.getValue(), taPostContent.getValue()));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
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
