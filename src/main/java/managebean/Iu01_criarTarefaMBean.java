package managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import modelo.dao.ManterTarefa;
import modelo.entity.Crianca;
import modelo.entity.Tarefa;


@RequestScoped
@ManagedBean
public class Iu01_criarTarefaMBean {
	
	private Tarefa tarefa = new Tarefa();
	private List<SelectItem> listaCombo = new ArrayList<SelectItem>();//Crie um ArrayList de SelectItem
	private List<Crianca> criancas = new ArrayList<Crianca>();
	private long crianca;
	
	public Iu01_criarTarefaMBean() {
		ManterTarefa manterTarefa = new ManterTarefa();
		setCriancas(manterTarefa.onBuscarCriancas());
		for(Crianca c : criancas) {
	        listaCombo.add(new SelectItem(c.getId(), c.getNome()));
		}
	}

	public void onSalvarTarefa() {
		ManterTarefa manterTarefa = new ManterTarefa();
		tarefa.setStatus("Disponível");
		manterTarefa.salvarTarefa(tarefa, crianca);
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<SelectItem> getListaCombo() {
		return listaCombo;
	}

	public void setListaCombo(List<SelectItem> listaCombo) {
		this.listaCombo = listaCombo;
	}

	public long getCrianca() {
		return crianca;
	}

	public void setCrianca(long crianca) {
		this.crianca = crianca;
	}

	public List<Crianca> getCriancas() {
		return criancas;
	}

	public void setCriancas(List<Crianca> criancas) {
		this.criancas = criancas;
	}
	
}