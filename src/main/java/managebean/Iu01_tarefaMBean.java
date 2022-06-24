package managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.entity.Sala;
import modelo.entity.Tarefa;

@RequestScoped
@ManagedBean
public class Iu01_tarefaMBean {
	
	private String nomeTarefa;
	private String status;
	private String dscTarefa;
	private Double koin;
	private Sala sala;
	private Tarefa tarefa;
	
	@PostConstruct
	public void init() {
	}

}
