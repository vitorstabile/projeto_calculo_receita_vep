package model.entities.pk;

import java.io.Serializable;

import model.entities.MP;
import model.entities.Receita;

public class IngredientePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MP mp;
	private Receita receita;
	
	
	public MP getMp() {
		return mp;
	}
	public void setMp(MP mp) {
		this.mp = mp;
	}
	public Receita getReceita() {
		return receita;
	}
	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mp == null) ? 0 : mp.hashCode());
		result = prime * result + ((receita == null) ? 0 : receita.hashCode());
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
		IngredientePK other = (IngredientePK) obj;
		if (mp == null) {
			if (other.mp != null)
				return false;
		} else if (!mp.equals(other.mp))
			return false;
		if (receita == null) {
			if (other.receita != null)
				return false;
		} else if (!receita.equals(other.receita))
			return false;
		return true;
	}

}
