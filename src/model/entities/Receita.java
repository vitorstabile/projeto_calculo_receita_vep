package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idReceita;
	private String descricaoReceita;
	private Double rendLiqReceita;
	private Double gramaturaReceita;
	private Double rendBrutoReceita;
	private Double perdaReceita;
	private Double custoReceita;
	
	private Teste teste;

	private List<Ingrediente> ingredientes = new ArrayList<>();

	public Receita() {
	}

	public Receita(Integer idReceita, String descricaoReceita, Double rendLiqReceita, Double gramaturaReceita) {
		this.idReceita = idReceita;
		this.descricaoReceita = descricaoReceita;
		this.rendLiqReceita = rendLiqReceita;
		this.gramaturaReceita = gramaturaReceita;
		this.rendBrutoReceita = null;
		this.perdaReceita = null;
		this.custoReceita = null;
		this.teste = null;
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
	
	public Teste getTeste() {
		return teste;
	}

	public void setTeste(Teste teste) {
		this.teste = teste;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredientes.add(ingrediente);
		updateRendBrutoReceita();
		updatePerdaReceita();
		updateCustoReceita();
		updatePorcenIngredientes();
	}

	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredientes.remove(ingrediente);
		updateRendBrutoReceita();
		updatePerdaReceita();
		updateCustoReceita();
		updatePorcenIngredientes();
	}

	public void updateRendBrutoReceita() {

		double soma = 0;

		for (Ingrediente ingrediente : ingredientes) {
			soma = soma + ingrediente.getQtIngrediente();
		}
		this.setRendBrutoReceita(soma);
	}

	public void updatePerdaReceita() {

		double perda = ((this.getRendBrutoReceita() - this.getRendLiqReceita()) / (this.getRendBrutoReceita()))* 100;

		this.setPerdaReceita(perda);
	}
	
	public void updateCustoReceita() {
		
		double soma = 0;

		for (Ingrediente ingrediente : ingredientes) {
			soma = soma + ingrediente.getCustoIngrediente();
		}
		
		double custo = soma / this.getRendLiqReceita();
		
		this.setCustoReceita(custo);
		
	}
	
	public void updatePorcenIngredientes() {

		for (Ingrediente ingrediente : ingredientes) {
			ingrediente.setPorcenIngrediente((ingrediente.getQtIngrediente()/this.getRendBrutoReceita())*100);
		}
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
				+ ", perdaReceita=" + perdaReceita + ", custoReceita=" + custoReceita + ", id_Teste=" + this.getTeste().getIdTeste() + ", ingrediente=" + ingredientes
				+ "]";
	}

}
