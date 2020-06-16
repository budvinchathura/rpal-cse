package cse;

public class Executer {
	private Control control;
	private Stack stack;
	private ControlStrcutureStore csStore;
	private int nextEnvId;

	public Executer(ControlStrcutureStore csStore) {
		this.csStore = csStore;
		this.nextEnvId = 0;
		
	}

}
