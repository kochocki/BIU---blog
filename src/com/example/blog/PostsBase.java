package com.example.blog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PostsBase {

	private static List<Post>	posts			= new ArrayList<Post>();
	private static File				postsFile	= new File("D:\\posts.csv");

	public static void add(Post post) throws IOException {
		posts.add(post);
		String postString = post.getTitle() + "," + post.getContent();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(postsFile, true), "UTF-8"));
		writer.write(postString);
		writer.newLine();
		writer.close();
	}
}
