package managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.dao.ManterTarefa;
import modelo.dao.ManterUsuario;
import modelo.entity.Sala;
import modelo.entity.Tarefa;
import modelo.entity.UsuarioLogado;

@RequestScoped
@ManagedBean
public class Iu01_tarefaMBean {

	private Tarefa tarefa;

	@PostConstruct
	public void init() {
	}

	public boolean pertenceResponsavel() {
		if (UsuarioLogado.getInstance().getUsuario().getIsResponsavel()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}