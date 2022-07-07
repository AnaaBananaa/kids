package managebean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import modelo.dao.ManterRecompensa;
import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

@RequestScoped
@ManagedBean
public class Iu01_recompensaMBean {

	private Recompensa recompensa;
	private Crianca crianca = new Crianca();

	public Iu01_recompensaMBean() {
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

	public List<Recompensa> retornaRecompensa() {
		if (!pertenceResponsavel()) {
			ManterRecompensa mt = new ManterRecompensa();
			return mt.onBuscarRecompensas();
		} else
			return null;
	}

	public void resgate(Recompensa rec) {
		if (rec.getPreco() <= crianca.getKoin()) {
			crianca.setKoin(crianca.getKoin() - rec.getPreco());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"RESGATE EFETUADO COM SUCESSO", "Avise seu responsável que resgatou a recompensa"));
			PrimeFaces.current().ajax().update("idform:growl");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO AO RESGATAR", "O valor da recompensa é maior que seu saldo"));
			PrimeFaces.current().ajax().update("idform:growl");
		}

	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

}
