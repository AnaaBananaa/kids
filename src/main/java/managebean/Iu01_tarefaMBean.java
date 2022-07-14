package managebean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

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
		ManterTarefa mt = new ManterTarefa();
		if (!pertenceResponsavel()) {
			return mt.onBuscarTarefas();
		} else
			return mt.onBuscarTarefasEspecificas();
	}
	
	public void validaTarefa(Tarefa tarefa) {
		ManterTarefa mt = new ManterTarefa();
		ManterUsuario mu = new ManterUsuario();
		mu.atualizaCrianca(tarefa.getKoin(), tarefa.getCrianca());
		mt.removerTarefa(tarefa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "VALIDAÇÃO FEITA COM SUCESSO", "Os koins foram enviados para a criança"));
		PrimeFaces.current().ajax().update("idform:growl");
	}
	
	public void reprovaTarefa(Tarefa tarefa) {
		ManterTarefa mt = new ManterTarefa();
		mt.removerTarefa(tarefa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "REMOÇÃO FEITA COM SUCESSO", ""));
		PrimeFaces.current().ajax().update("idform:growl");
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
