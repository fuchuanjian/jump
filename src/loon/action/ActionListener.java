package loon.action;


public interface ActionListener {

	public void start(ActionBind o);
	
	public void process(ActionBind o);
	
	public void stop(ActionBind o);
	
}

