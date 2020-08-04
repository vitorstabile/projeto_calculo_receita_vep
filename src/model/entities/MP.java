package model.entities;

import java.io.Serializable;

public class MP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String codigoMP;
	private String descricaoMP;
	private Double custoMP;
	
	
	public MP() {
	}


	public MP(Integer id, String codigoMP, String descricaoMP, Double custoMP) {
		this.id = id;
		this.codigoMP = codigoMP;
		this.descricaoMP = descricaoMP;
		this.custoMP = custoMP;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodigoMP() {
		return codigoMP;
	}


	public void setCodigoMP(String codigoMP) {
		this.codigoMP = codigoMP;
	}


	public String getDescricaoMP() {
		return descricaoMP;
	}


	public void setDescricaoMP(String descricaoMP) {
		this.descricaoMP = descricaoMP;
	}


	public Double getCustoMP() {
		return custoMP;
	}


	public void setCustoMP(Double custoMP) {
		this.custoMP = custoMP;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MP other = (MP) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "MP [id=" + id + ", codigoMP=" + codigoMP + ", descricaoMP=" + descricaoMP + ", custoMP=" + custoMP + "]";
	}
	
	
}
