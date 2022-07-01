package managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.dao.ManterTarefa;
import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Tarefa;
import modelo.entity.UsuarioLogado;

@RequestScoped
@ManagedBean
public class Iu01_tarefaMBean {

	private Tarefa tarefa;
	private Crianca crianca = new Crianca();

	public Iu01_tarefaMBean() {
		criancaLogada();
	}

	public boolean pertenceResponsavel() {
		if (UsuarioLogado.getInstance().getUsuario().getIsResponsavel()) {
			return true;
		} else {
			return false;
		}
	}

	public void criancaLogada() {
		ManterUsuario u = new ManterUsuario();
		if (u.findCrianca(UsuarioLogado.getInstance().getUsuario().getId()) != null) {
			crianca = u.findCrianca(UsuarioLogado.getInstance().getUsuario().getId());
		}
	}

	public List<Tarefa> retornaTarefa() {
		if (!pertenceResponsavel()) {
			ManterTarefa mt = new ManterTarefa();
			return mt.onBuscarTarefas();
		} else
			return null;
	}

	public void mudarStatus(Tarefa tarefa) {
		ManterTarefa mt = new ManterTarefa();
		mt.atualizaStatus(tarefa);
	}

	public boolean desabilitaBotao(Tarefa tarefa) {
		if (tarefa.getStatus().equals("Aguardando Avaliação")) {
			return true;
		} else
			return false;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

}
