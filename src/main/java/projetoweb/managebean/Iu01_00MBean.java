package projetoweb.managebean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;

import dao.ManterResponsavel;
import dao.ManterUsuario;
import modelos.Crianca;
import modelos.Responsavel;
import modelos.Usuario;

@RequestScoped
@ManagedBean
public class Iu01_00MBean {
	private String confirmSenha;
	private Responsavel user = new Responsavel();
	private boolean skip;

	@PostConstruct
	public void init() {
		System.out.println(" Bean 2 executado! ");
	}
	
	public void onSalvarResponsavel() {
		ManterResponsavel manterResp = new ManterResponsavel();
		manterResp.salvarResponsavel(user);
	}
	
	public void onSalvarCrianca() {
		user.setIsResponsavel(true);
		ManterUsuario<Usuario> manterUsuario = new ManterUsuario<Usuario>();
	}
	
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
	public String getConfirmSenha() {
		return confirmSenha;
	}

	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}

	public Responsavel getUser() {
		return user;
	}

	public void setUser(Responsavel user) {
		this.user = user;
	}

	

}
