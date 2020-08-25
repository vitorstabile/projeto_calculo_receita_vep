package model.entities;

import java.io.Serializable;

public class Recurso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idRecurso;
	private String descricaoRecurso;
	private Double custoRecurso;
	
	
	public Recurso() {
	}

	public Recurso(Integer idRecurso, String descricaoRecurso, Double custoRecurso) {
		this.idRecurso = idRecurso;
		this.descricaoRecurso = descricaoRecurso;
		this.custoRecurso = custoRecurso;
	}

	public Integer getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getDescricaoRecurso() {
		return descricaoRecurso;
	}

	public void setDescricaoRecurso(String descricaoRecurso) {
		this.descricaoRecurso = descricaoRecurso;
	}

	public Double getCustoRecurso() {
		return custoRecurso;
	}

	public void setCustoRecurso(Double custoRecurso) {
		this.custoRecurso = custoRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRecurso == null) ? 0 : idRecurso.hashCode());
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
		Recurso other = (Recurso) obj;
		if (idRecurso == null) {
			if (other.idRecurso != null)
				return false;
		} else if (!idRecurso.equals(other.idRecurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recurso [idRecurso=" + idRecurso + ", descricaoRecurso=" + descricaoRecurso + ", custoRecurso="
				+ custoRecurso + "]";
	}
	
}
