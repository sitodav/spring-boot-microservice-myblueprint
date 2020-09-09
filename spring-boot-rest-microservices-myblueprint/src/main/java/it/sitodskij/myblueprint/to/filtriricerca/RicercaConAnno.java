package it.sitodskij.myblueprint.to.filtriricerca;

public class RicercaConAnno extends FiltroRicercaPaginata {

	private static final long serialVersionUID = 8323064754038190825L;
	
	private Long anno;

	public Long getAnno() {
		return anno;
	}

	public void setAnno(Long anno) {
		this.anno = anno;
	}

	
	@Override
	public String toString() {
		return "RicercaConAnno [anno=" + anno + "]";
	}
	
	
	
	
}
