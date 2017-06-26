package by.pzh.jedi.domain;

import java.io.Serializable;

public class Result<T extends Serializable> implements Serializable {
	
	private boolean ok;
	private  T target;
	
	
	public boolean isOk() {
		return ok;
	}
	public Result<T> setOk(boolean ok) {
		this.ok = ok;
		return this;
	}
	public T getTarget() {
		return target;
	}
	public Result<T> setTarget(T target) {
		this.target = target;
		return this;
	}
	
	

}
