import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import managebean.Iu01_recompensaMBean;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

public class ResgateRecompensaTest extends TestCase{
	
	private Recompensa rec;
	private Crianca crianca;
	private Iu01_recompensaMBean mbean;
	
	@Before
	protected void setUp() throws Exception {
		rec = new Recompensa();
		rec.setPreco(5.00);
		crianca = new Crianca();
		crianca.setId((long) 1);
		UsuarioLogado.getInstance().setUsuario(crianca);
		mbean = new Iu01_recompensaMBean();
	}

	@Test
	public void testCase1() {
		crianca.setKoin(5.00);
		boolean retorno = mbean.validaPreco(rec, crianca.getKoin());
		assertEquals(true, retorno);
	}
	
	@Test
	public void testCase2() {
		crianca.setKoin(10.00);
		boolean retorno = mbean.validaPreco(rec, crianca.getKoin());
		assertEquals(true, retorno);
	}
	
	@Test
	public void testCase3() {
		crianca.setKoin(4.00);
		boolean retorno = mbean.validaPreco(rec, crianca.getKoin());
		assertEquals(false, retorno);
	}

}
