package it.sitodskij.myblueprint.to;

import java.io.Serializable;
import java.util.List;

public class CountableWrapper<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2972062131555468801L;
	
	private List<T> wrappedResult;
	private long totElements;
	private long totPages;
	
	
	public CountableWrapper(List<T> wrappedResult, long totElements, long totPages) {
		 
		this.wrappedResult = wrappedResult;
		this.totElements = totElements;
		this.totPages = totPages;
	}
	
	public List<T> getWrappedResult() {
		return wrappedResult;
	}
	public void setWrappedResult(List<T> wrappedResult) {
		this.wrappedResult = wrappedResult;
	}
	public long getTotElements() {
		return totElements;
	}
	public void setTotElements(long totElements) {
		this.totElements = totElements;
	}

	public long getTotPages() {
		return totPages;
	}

	public void setTotPages(long totPages) {
		this.totPages = totPages;
	}
	
	

}
