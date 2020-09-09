package it.sitodskij.myblueprint.to.filtriricerca;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiltroRicercaPaginata implements Serializable {
	
	
	private static final long serialVersionUID = -1490901160391122239L;
	
	private Long p;
	private Long psize;
	private Boolean deepSearch = false;
	private Map<String,String> columnFilter = new HashMap<>(); //chiave REF COLONNA : INDICE COLONNA 
	private List<String> sortBy;
	private String sortDir;
	
	public Long getP() {
		return p;
	}
	public void setP(Long p) {
		this.p = Math.max(0L, p);
	}
	public Long getPsize() {
		return psize;
	}
	public void setPsize(Long psize) {
		this.psize = Math.max(1L, psize);
	}
	 
	public Boolean getDeepSearch() {
		return deepSearch;
	}
	public void setDeepSearch(Boolean deepSearch) {
		this.deepSearch = deepSearch;
	}
	
	
	
	public Map<String, String> getColumnFilter() {
		return columnFilter;
	}
	public void setColumnFilter(Map<String, String> columnFilter) {
		this.columnFilter = columnFilter;
	}
	
	
	
	public List<String> getSortBy() {
		return sortBy;
	}
	public void setSortBy(List<String> sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	
	@Override
	public String toString() {
		return "FiltroRicercaPaginata [p=" + p + ", psize=" + psize + ", deepSearch=" + deepSearch + ", columnFilter="
				+ columnFilter + ", sortBy=" + sortBy + ", sortDir=" + sortDir + "]";
	}
	 
	
	
	
	
	
}
