package managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import modelo.dao.ManterTarefa;
import modelo.entity.Crianca;
import modelo.entity.Tarefa;


@RequestScoped
@ManagedBean
public class Iu01_criarTarefaMBean {
	
	private Tarefa tarefa = new Tarefa();
	private List<Crianca> criancas = new ArrayList<Crianca>();
	List<SelectItem> listaCombo = new ArrayList<SelectItem>();//Crie um ArrayList de SelectItem
	private Crianca crianca;

	@PostConstruct
	public void init() {
		onBuscarCriancas();
	}

	public void onSalvarTarefa() {
		ManterTarefa manterTarefa = new ManterTarefa();
		tarefa.setStatus("Disponível");
		tarefa.setCrianca(crianca);
		manterTarefa.salvarTarefa(tarefa);
	}
	
	public void onBuscarCriancas() {
		ManterTarefa manterTarefa = new ManterTarefa();
		setCriancas(manterTarefa.onBuscarCriancas());
		for(Crianca c : criancas) {
	        listaCombo.add(new SelectItem(c.getNome()));
		}
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Crianca> getCriancas() {
		return criancas;
	}

	public void setCriancas(List<Crianca> criancas) {
		this.criancas = criancas;
	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}
	
}