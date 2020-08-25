package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teste implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idTeste;
	private String descricaoTeste;
	private Double baseCalcTeste;
	private Double gramaturaTeste;
	private Double rendBrutoTeste;
	private Double custoTeste;
	
	private List<Receita> receitas = new ArrayList<>();

	public Teste() {
	}

	public Teste(Integer idTeste, String descricaoTeste, Double baseCalcTeste) {
		this.idTeste = idTeste;
		this.descricaoTeste = descricaoTeste;
		this.baseCalcTeste = baseCalcTeste;
		this.gramaturaTeste = null;
		this.rendBrutoTeste = null;
		this.custoTeste = null;
	}

	public Integer getIdTeste() {
		return idTeste;
	}

	public void setIdTeste(Integer idTeste) {
		this.idTeste = idTeste;
	}

	public String getDescricaoTeste() {
		return descricaoTeste;
	}

	public void setDescricaoTeste(String descricaoTeste) {
		this.descricaoTeste = descricaoTeste;
	}

	public Double getBaseCalcTeste() {
		return baseCalcTeste;
	}

	public void setBaseCalcTeste(Double baseCalcTeste) {
		this.baseCalcTeste = baseCalcTeste;
	}

	public Double getGramaturaTeste() {
		return gramaturaTeste;
	}

	public void setGramaturaTeste(Double gramaturaTeste) {
		this.gramaturaTeste = gramaturaTeste;
	}

	public Double getRendBrutoTeste() {
		return rendBrutoTeste;
	}

	public void setRendBrutoTeste(Double rendBrutoTeste) {
		this.rendBrutoTeste = rendBrutoTeste;
	}

	public Double getCustoTeste() {
		return custoTeste;
	}

	public void setCustoTeste(Double custoTeste) {
		this.custoTeste = custoTeste;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}
	
	public void addReceita(Receita receita) {
		this.receitas.add(receita);
	}
	
	public void removeReceita(Receita receita) {
		this.receitas.remove(receita);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTeste == null) ? 0 : idTeste.hashCode());
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
		Teste other = (Teste) obj;
		if (idTeste == null) {
			if (other.idTeste != null)
				return false;
		} else if (!idTeste.equals(other.idTeste))
			return false;
		return true;
	}

}
