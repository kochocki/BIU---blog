package com.example.blog;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//https://vaadin.com/wiki/-/wiki/Main/Broadcasting+messages+to+other+users
public class Broadcaster {

	private static final List<BroadcastListener> listeners = new CopyOnWriteArrayList<BroadcastListener>();

	public static void register(BroadcastListener listener) {
		listeners.add(listener);
	}

	public static void unregister(BroadcastListener listener) {
		listeners.remove(listener);
	}

	public static void broadcast(final Post message) {
		for (BroadcastListener listener : listeners) {
			listener.receiveBroadcast(message);
		}
	}

	public interface BroadcastListener {

		public void receiveBroadcast(Post message);
	}
}
