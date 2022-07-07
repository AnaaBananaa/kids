package managebean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Responsavel;
import modelo.entity.Sala;
import modelo.entity.Usuario;
import modelo.entity.UsuarioLogado;

@ViewScoped
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
	private List<Responsavel> resp;
	private List<Crianca> crianca;
	private String sala;
	private int Resp = 2;

	public void onSalvarResponsavel() {
		userResp.setNome(user.getNome());
		userResp.setDataNascimento(user.getDataNascimento());
		userResp.setEmail(user.getEmail());
		userResp.setSenha(user.getSenha());
		userResp.setIsResponsavel(true);
		ManterUsuario manterResp = new ManterUsuario();
		manterResp.salvarResponsavel(userResp);
	}
	
	public void onSalvar() {
		if (user instanceof Responsavel) {
			onSalvarResponsavel();
		}else if (user instanceof Crianca){
			cadastoCrianca();
		}
	}

	public void cadastoCrianca() {
		ManterUsuario manterUsuario = new ManterUsuario();
		if (manterUsuario.onBuscarSala(sala) != null) {
			userCrianca.setSalas(new ArrayList<Sala>());
			userCrianca.setDataNascimento(user.getDataNascimento());
			userCrianca.setNome(user.getNome());
			userCrianca.setEmail(user.getEmail());
			userCrianca.setSenha(user.getSenha());
			userCrianca.setIsResponsavel(false);
			userCrianca.setKoin(0.00);
			userCrianca.getSalas().add(manterUsuario.onBuscarSala(sala));
			manterUsuario.salvarCrianca(userCrianca);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "CADASTRO EFETUADO COM SUCESSO", "Cadastro feito com sucesso"));
			PrimeFaces.current().ajax().update("idformLogin:growl");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO AO CADASTRAR", "Sala inexistente"));
			PrimeFaces.current().ajax().update("idformLogin:growl");
		}

	}
	
	public void instanciaDe(int i) {
		if(i == 1) {
			user = new Responsavel();
		}
		else if(i == 0) {
			user = new Crianca();
		}
		
	}

	public void onBuscarUsuario() throws Exception {
		ManterUsuario manterUsuario = new ManterUsuario();
		resp = manterUsuario.onBuscarResp(getEmail(), getSenha());
		crianca = manterUsuario.onBuscarCrianca(getEmail(), getSenha());
		validaPagina();
	}

	public void validaPagina() throws Exception {
		if (resp.size() > 0) {
			UsuarioLogado.getInstance().setUsuario(resp.get(0));
			FacesContext.getCurrentInstance().getExternalContext().redirect("iu01_tarefa.jsf");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGIN EFETUADO COM SUCESSO", "Login feito com sucesso"));
			PrimeFaces.current().ajax().update("idformLogin:growl");
		} else if (crianca.size() > 0) {
			UsuarioLogado.getInstance().setUsuario(crianca.get(0));
			FacesContext.getCurrentInstance().getExternalContext().redirect("iu01_tarefa.jsf");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGIN EFETUADO COM SUCESSO", "Login feito com sucesso"));
			PrimeFaces.current().ajax().update("idformLogin:growl");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ERRO AO LOGAR", "Verifique se o email/senha estão corretos"));
			PrimeFaces.current().ajax().update("idformLogin:growl");
		}
	}
	
	public void clearMultiViewState() {
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		PrimeFaces.current().multiViewState().clearAll(viewId, true);
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

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public int getResp() {
		return Resp;
	}

	public void setResp(int resp) {
		Resp = resp;
	}
	
}
