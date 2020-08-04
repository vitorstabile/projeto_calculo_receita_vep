package model.entities;

import java.io.Serializable;

public class MP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idMP;
	private String codigoMP;
	private String descricaoMP;
	private Double custoMP;
	
	
	public MP() {
	}


	public MP(Integer idMP, String codigoMP, String descricaoMP, Double custoMP) {
		this.idMP = idMP;
		this.codigoMP = codigoMP;
		this.descricaoMP = descricaoMP;
		this.custoMP = custoMP;
	}


	public Integer getId() {
		return idMP;
	}


	public void setId(Integer idMP) {
		this.idMP = idMP;
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
		result = prime * result + ((idMP == null) ? 0 : idMP.hashCode());
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
		if (idMP == null) {
			if (other.idMP != null)
				return false;
		} else if (!idMP.equals(other.idMP))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "MP [idMP=" + idMP + ", codigoMP=" + codigoMP + ", descricaoMP=" + descricaoMP + ", custoMP=" + custoMP
				+ "]";
	}


	
	
	
}
