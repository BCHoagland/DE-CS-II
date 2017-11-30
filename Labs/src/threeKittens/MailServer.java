package threeKittens;

import java.util.LinkedList;
import java.util.TreeSet;

public class MailServer extends LinkedList<Message> {
	
	private TreeSet<Actor> registeredUsers = new TreeSet<Actor>();
	
	public void signUp(Actor actor) {
		if (!registeredUsers.contains(actor)) {
			registeredUsers.add(actor);
		}
	}
	
	public void dispatch(Message msg) {
		Actor recipient = msg.getRecipient();
		if (recipient != null) {
			recipient.receive(msg);
		} else {
			for (Actor actor : registeredUsers) {
				actor.receive(msg);
			}
		}
	}
}