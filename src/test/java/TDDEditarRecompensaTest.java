import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.dao.ManterRecompensa;
import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

public class TDDEditarRecompensaTest {
	
	private ManterRecompensa manter = new ManterRecompensa();
	private Crianca crianca = new Crianca();
	
	@Before
	private void setUp() {
		ManterUsuario manterUsuario = new ManterUsuario();
		crianca = manterUsuario.onBuscarCrianca("teste@gmail.com", "teste");
		UsuarioLogado.getInstance().setUsuario(crianca);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Recompensa recompensa = manter.onBuscarRecompensas() != null ? manter.onBuscarRecompensas().get(0) : null;
		recompensa.setDescricao("Teste TDD");
		recompensa.setNomeRecompensa("Teste TDD");
		recompensa.setPreco(50.00);
		assertEquals(true, manter.atualizarRecompensa(recompensa));
}

}
