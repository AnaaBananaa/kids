package modelo.entity;

public class UsuarioLogado {
	
    private static UsuarioLogado instance;
	private Usuario usuario;
	
	public synchronized static UsuarioLogado getInstance() {
		if (instance == null)
			instance = new UsuarioLogado();
		
		return instance;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
