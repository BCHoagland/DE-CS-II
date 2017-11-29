package browser;

import java.util.Stack;

public class BrowserModel {
	
	private BrowserView view;
	private Stack<Integer> backStack, fwdStack;
	private int lineNum;
	
	public BrowserModel(BrowserView view) {
		this.view = view;
		home();
	}
	
	public void back() {
		if (hasBack()) {
			fwdStack.push(lineNum);
			lineNum = backStack.pop();
			view.update(lineNum);
		}
	}

	public void forward() {
		if (hasForward()) {
			
		}
	}
	
	public void home() {
		followLink(0);
		backStack.clear();
//		fwdStack.clear();
	}
	
	public void followLink(int n) {
		fwdStack.clear();
		backStack.push(lineNum);
		lineNum = n;
		view.update(n);
	}
	
	public boolean hasBack() {
		return !backStack.isEmpty();
	}
	
	public boolean hasForward() {
		return !fwdStack.isEmpty();
	}
}