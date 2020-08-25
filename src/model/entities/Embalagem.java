package model.entities;

import java.io.Serializable;

import model.entities.pk.EmbalagemPK;

public class Embalagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EmbalagemPK id_Teste_Receita = new EmbalagemPK();
	
	private Integer id_Teste_Receita_PK;
	private Double custoMP;
	private Double qtEmbalagem;
	private Double custoEmbalagem;
	
	
	public Embalagem() {
	}


	public Embalagem(Integer id_Teste_Receita_PK, MP mp, Teste teste, Double qtEmbalagem) {
		this.id_Teste_Receita_PK = id_Teste_Receita_PK;
		id_Teste_Receita.setMp(mp);
		id_Teste_Receita.setTeste(teste);
		this.custoMP = mp.getCustoMP();
		this.qtEmbalagem = qtEmbalagem;
		this.custoEmbalagem = mp.getCustoMP() * qtEmbalagem;
	}
	
	public MP getMP() {
		return id_Teste_Receita.getMp();
	}

	public void setMP(MP mp) {
		id_Teste_Receita.setMp(mp);
	}
	
	public Teste getTeste() {
		return id_Teste_Receita.getTeste();
	}
	
	public void setTeste(Teste teste) {
		id_Teste_Receita.setTeste(teste);
	}


	public Integer getId_Teste_Receita_PK() {
		return id_Teste_Receita_PK;
	}


	public void setId_Teste_Receita_PK(Integer id_Teste_Receita_PK) {
		this.id_Teste_Receita_PK = id_Teste_Receita_PK;
	}


	public Double getCustoMP() {
		return custoMP;
	}


	public void setCustoMP(Double custoMP) {
		this.custoMP = custoMP;
	}


	public Double getQtEmbalagem() {
		return qtEmbalagem;
	}


	public void setQtEmbalagem(Double qtEmbalagem) {
		this.qtEmbalagem = qtEmbalagem;
	}


	public Double getCustoEmbalagem() {
		return custoEmbalagem;
	}


	public void setCustoEmbalagem(Double custoEmbalagem) {
		this.custoEmbalagem = custoEmbalagem;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_Teste_Receita_PK == null) ? 0 : id_Teste_Receita_PK.hashCode());
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
		Embalagem other = (Embalagem) obj;
		if (id_Teste_Receita_PK == null) {
			if (other.id_Teste_Receita_PK != null)
				return false;
		} else if (!id_Teste_Receita_PK.equals(other.id_Teste_Receita_PK))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Embalagem [id_Teste_Receita_PK=" + id_Teste_Receita_PK + ", id_MP=" + this.getMP().getId() + ", id_Teste=" + this.getTeste().getIdTeste()
				+ ", custoMP=" + custoMP + ", qtEmbalagem="
				+ qtEmbalagem + ", custoEmbalagem=" + custoEmbalagem + "]";
	}
	
	

}
