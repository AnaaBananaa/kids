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

	private String nome = "";
	private String email = "";
	private String senha = "";
	private String confirmSenha= "";
	private Date dataNasc;
	private boolean isResponsavel;
	private Responsavel user = new Responsavel();
	private boolean skip;

	@PostConstruct
	public void init() {
		System.out.println(" Bean 2 executado! ");
	}
	
	public void onSalvarResponsavel() {
		ManterResponsavel manterResp = new ManterResponsavel();
		Responsavel resp = new Responsavel();
		resp.setNome(nome);
		resp.setEmail(email);
		resp.setSenha(senha);
		resp.setDataNascimento(dataNasc);
		resp.setIsResponsavel(isResponsavel);
		manterResp.salvarResponsavel(resp);
	}
	
	public void onSalvarCrianca() {
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
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public boolean isResponsavel() {
		return isResponsavel;
	}

	public void setResponsavel(boolean isResponsavel) {
		this.isResponsavel = isResponsavel;
	}
	

}
