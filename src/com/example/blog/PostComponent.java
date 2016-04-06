package com.example.blog;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PostComponent extends CustomField<Post> {

	private static final long	serialVersionUID	= -5788741510670182641L;
	private TextField					tfPostTitle				= new TextField();
	private TextArea					taPostContent			= new TextArea();
	private FieldGroup				fieldGroup				= new BeanFieldGroup<Post>(Post.class);

	@Override
	protected Component initContent() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.addComponent(tfPostTitle);
		layout.addComponent(taPostContent);
		//
		tfPostTitle.setEnabled(false);
		taPostContent.setReadOnly(false);
		this.setReadOnly(false);
		// binding
		fieldGroup.bind(tfPostTitle, "title");
		fieldGroup.bind(taPostContent, "content");
		try {
			fieldGroup.commit();
		}
		catch (CommitException e) {
			e.printStackTrace();
		}
		return layout;
	}

	@Override
	public Class<? extends Post> getType() {
		return Post.class;
	}

	@Override
	protected void setInternalValue(Post post) {
		super.setInternalValue(post);
		fieldGroup.setItemDataSource(new BeanItem<Post>(post));
	}
}
