package managebean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.entity.Usuario;

@RequestScoped
@ManagedBean
public class IndexMBean {
	
	private Usuario usuario = new Usuario();

	@PostConstruct
	public void init() {
		System.out.println(" Bean executado! ");
	}

	public String getMessage() {
		return "Hello World JSF!asdaw";
	}
	
	public void encaminhar() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext()
        .redirect("iu01_00.jsf");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}