package managebean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.entity.Usuario;
import modelo.entity.UsuarioLogado;

@RequestScoped
@ManagedBean
public class Iu01_perfilMBean {
	
	private Usuario user;

	public Iu01_perfilMBean() {
		this.user = UsuarioLogado.getInstance().getUsuario();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario usuario) {
		this.user = usuario;
	}
	
}
