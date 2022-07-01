package managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import modelo.dao.ManterRecompensa;
import modelo.dao.ManterTarefa;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.Tarefa;


@RequestScoped
@ManagedBean
public class Iu01_criarRecompensaMBean {
	
	private Recompensa recompensa = new Recompensa();
	private List<SelectItem> listaCombo = new ArrayList<SelectItem>();//Crie um ArrayList de SelectItem
	private List<Crianca> criancas = new ArrayList<Crianca>();
	private long crianca;

	public Iu01_criarRecompensaMBean() {
		ManterTarefa manterTarefa = new ManterTarefa();
		setCriancas(manterTarefa.onBuscarCriancas());
		for(Crianca c : criancas) {
	        listaCombo.add(new SelectItem(c.getId(), c.getNome()));
		}
	}

	public void onSalvarRecompensa() {
		ManterRecompensa manterRecompensa = new ManterRecompensa();
		manterRecompensa.salvarRecompensa(recompensa, crianca);
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public List<SelectItem> getListaCombo() {
		return listaCombo;
	}

	public void setListaCombo(List<SelectItem> listaCombo) {
		this.listaCombo = listaCombo;
	}

	public List<Crianca> getCriancas() {
		return criancas;
	}

	public void setCriancas(List<Crianca> criancas) {
		this.criancas = criancas;
	}

	public long getCrianca() {
		return crianca;
	}

	public void setCrianca(long crianca) {
		this.crianca = crianca;
	}
	
	
}