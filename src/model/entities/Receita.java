package model.entities;

import java.io.Serializable;

public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idReceita;
	private String descricaoReceita;
	private Double rendLiqReceita;
	private Double gramaturaReceita;
	private Double rendBrutoReceita;
	private Double perdaReceita;
	private Double custoReceita;
	
	
	public Receita() {
	}


	public Receita(Integer idReceita, String descricaoReceita, Double rendLiqReceita, Double gramaturaReceita) {
		this.idReceita = idReceita;
		this.descricaoReceita = descricaoReceita;
		this.rendLiqReceita = rendLiqReceita;
		this.gramaturaReceita = gramaturaReceita;
	}


	public Integer getIdReceita() {
		return idReceita;
	}


	public void setIdReceita(Integer idReceita) {
		this.idReceita = idReceita;
	}


	public String getDescricaoReceita() {
		return descricaoReceita;
	}


	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}


	public Double getRendLiqReceita() {
		return rendLiqReceita;
	}


	public void setRendLiqReceita(Double rendLiqReceita) {
		this.rendLiqReceita = rendLiqReceita;
	}


	public Double getGramaturaReceita() {
		return gramaturaReceita;
	}


	public void setGramaturaReceita(Double gramaturaReceita) {
		this.gramaturaReceita = gramaturaReceita;
	}


	public Double getRendBrutoReceita() {
		return rendBrutoReceita;
	}


	public void setRendBrutoReceita(Double rendBrutoReceita) {
		this.rendBrutoReceita = rendBrutoReceita;
	}


	public Double getPerdaReceita() {
		return perdaReceita;
	}


	public void setPerdaReceita(Double perdaReceita) {
		this.perdaReceita = perdaReceita;
	}


	public Double getCustoReceita() {
		return custoReceita;
	}


	public void setCustoReceita(Double custoReceita) {
		this.custoReceita = custoReceita;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idReceita == null) ? 0 : idReceita.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;
		if (idReceita == null) {
			if (other.idReceita != null)
				return false;
		} else if (!idReceita.equals(other.idReceita))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Receita [idReceita=" + idReceita + ", descricaoReceita=" + descricaoReceita + ", rendLiqReceita="
				+ rendLiqReceita + ", gramaturaReceita=" + gramaturaReceita + ", rendBrutoReceita=" + rendBrutoReceita
				+ ", perdaReceita=" + perdaReceita + ", custoReceita=" + custoReceita + "]";
	}
	
	
	
	
}