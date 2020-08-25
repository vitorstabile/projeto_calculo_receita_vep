package model.entities.pk;

import java.io.Serializable;

import model.entities.MP;
import model.entities.Teste;

public class EmbalagemPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MP mp;
	private Teste teste;
	
	public MP getMp() {
		return mp;
	}
	
	public void setMp(MP mp) {
		this.mp = mp;
	}

	public Teste getTeste() {
		return teste;
	}

	public void setTeste(Teste teste) {
		this.teste = teste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mp == null) ? 0 : mp.hashCode());
		result = prime * result + ((teste == null) ? 0 : teste.hashCode());
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
		EmbalagemPK other = (EmbalagemPK) obj;
		if (mp == null) {
			if (other.mp != null)
				return false;
		} else if (!mp.equals(other.mp))
			return false;
		if (teste == null) {
			if (other.teste != null)
				return false;
		} else if (!teste.equals(other.teste))
			return false;
		return true;
	}
	
	

}
