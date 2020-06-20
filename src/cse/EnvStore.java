package cse;

import java.util.HashMap;

public class EnvStore {
	HashMap<Integer, Environment> envs;

	public EnvStore() {
		// TODO Auto-generated constructor stub
		envs = new HashMap<>();
	}
	
	public void addEnv(int id, Environment env) {
		this.envs.put(id, env);
	}
	
	public Environment getEnv(int id) throws Exception {
		if (!(this.envs.containsKey(id))) {
			throw new Exception("invalid environment");
		}
		return this.envs.get(id);
	}

}
