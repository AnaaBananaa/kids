package managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.dao.ManterRecompensa;
import modelo.dao.ManterTarefa;
import modelo.entity.Recompensa;
import modelo.entity.Tarefa;


@RequestScoped
@ManagedBean
public class Iu01_criarRecompensaMBean {
	
	private Recompensa recompensa = new Recompensa();

	@PostConstruct
	public void init() {
	}

	public void onSalvarRecompensa() {
		ManterRecompensa manterRecompensa = new ManterRecompensa();
		manterRecompensa.salvarRecompensa(recompensa);
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}
	
}