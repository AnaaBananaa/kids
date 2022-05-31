package modelos;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Crianca extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double koin;

	public Double getKoin() {
		return koin;
	}

	public void setKoin(Double koin) {
		this.koin = koin;
	}

}
