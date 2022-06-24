package managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.dao.ManterTarefa;
import modelo.entity.Tarefa;


@RequestScoped
@ManagedBean
public class Iu01_criarTarefaMBean {
	
	private Tarefa tarefa = new Tarefa();

	@PostConstruct
	public void init() {
	}

	public void onSalvarTarefa() {
		ManterTarefa manterTarefa = new ManterTarefa();
		tarefa.setStatus("Disponível");
		manterTarefa.salvarTarefa(tarefa);
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}