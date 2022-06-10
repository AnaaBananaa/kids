package managebean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FlowEvent;

import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Responsavel;
import modelo.entity.Usuario;

@RequestScoped
@ManagedBean
public class Iu01_00MBean {
	private String confirmSenha;
	private String nome;
	private String email;
	private Date dataNasc;
	private String senha;
	private Responsavel userResp = new Responsavel();
	private Crianca userCrianca = new Crianca();
	private Usuario user = new Usuario();
	private boolean skip;
	private boolean cadastro;

	@PostConstruct
	public void init() {
		System.out.println(" Bean 2 executado! ");
		setCadastro(false);
	}
	
	public void onSalvarResponsavel() {
		userResp.setDataNascimento(dataNasc);
		userResp.setNome(nome);
		userResp.setEmail(email);
		userResp.setSenha(senha);
		userResp.setIsResponsavel(true);
		ManterUsuario manterResp = new ManterUsuario();
		manterResp.salvarResponsavel(userResp);
	}
	
	public void onSalvarCrianca() {
		userCrianca.setDataNascimento(dataNasc);
		userCrianca.setNome(nome);
		userCrianca.setEmail(email);
		userCrianca.setSenha(senha);
		userCrianca.setIsResponsavel(false);
		userCrianca.setKoin(0.00);
		ManterUsuario manterUsuario = new ManterUsuario();
		manterUsuario.salvarCrianca(userCrianca);
	}
	
	public void onBuscarUsuario() {
		ManterUsuario manterUsuario = new ManterUsuario();
		email = "ana@gmail.com";
		senha = "123";
		manterUsuario.onBuscarUsuario(email, senha);
	}
	
	public void mudaCadastroLogin() {
		cadastro = true;
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

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Responsavel getUserResp() {
		return userResp;
	}

	public void setUserResp(Responsavel userResp) {
		this.userResp = userResp;
	}

	public Crianca getUserCrianca() {
		return userCrianca;
	}

	public void setUserCrianca(Crianca userCrianca) {
		this.userCrianca = userCrianca;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

}
