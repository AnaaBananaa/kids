import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import modelo.dao.ManterRecompensa;
import modelo.dao.ManterUsuario;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

public class CadastroRecompensaTest extends TestCase{
	
	private Crianca crianca;
	
	@Before
	public void setUp() {
		ManterUsuario manterUsuario = new ManterUsuario();
		crianca = manterUsuario.onBuscarCrianca("teste@gmail.com", "teste");
		UsuarioLogado.getInstance().setUsuario(crianca);
	}
	
	@Test
	public void testCase1() {
		Recompensa recompensa = new Recompensa();
		recompensa.setNomeRecompensa("Teste Unitário");
		recompensa.setDescricao("Teste Unitário de entradas válidas");
		recompensa.setPreco(10.00);
		ManterRecompensa manterRecompensa = new ManterRecompensa();
		assertEquals(false, achouRecompensa(manterRecompensa.onBuscarRecompensas(), recompensa));
		manterRecompensa.salvarRecompensa(recompensa, crianca.getId());
		assertEquals(true, achouRecompensa(manterRecompensa.onBuscarRecompensas(), recompensa));
	}

	@Test
	public void testCase2() {
		Recompensa recompensa = new Recompensa();
		recompensa.setNomeRecompensa("");
		recompensa.setDescricao("");
		recompensa.setPreco(0.00);
		ManterRecompensa manterRecompensa = new ManterRecompensa();
		assertEquals(false, achouRecompensa(manterRecompensa.onBuscarRecompensas(), recompensa));
		manterRecompensa.salvarRecompensa(recompensa, crianca.getId());
		assertEquals(true, achouRecompensa(manterRecompensa.onBuscarRecompensas(), recompensa));
	}
	
	private boolean achouRecompensa(List<Recompensa> recompensas, Recompensa recompensa) {
		for(Recompensa r : recompensas) {
			if((r.getNomeRecompensa().equals(recompensa.getNomeRecompensa()))&& (r.getDescricao().equals(recompensa.getDescricao())) &&
					(r.getPreco() == recompensa.getPreco())) {
				return true;
			}}
		return false;
	}
	
}