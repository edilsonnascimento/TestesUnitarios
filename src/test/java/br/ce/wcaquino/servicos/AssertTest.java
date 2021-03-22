package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test(){

        //booleano(condicao)
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        //Equals(esperado, atual)
        Assert.assertEquals(1,1);
        //Negado
        Assert.assertNotEquals(1,2);

        //Equals(esperado, atual, delta)
        Assert.assertEquals(0.51, 0.51, 0.01);

        //Wappers
        int pi = 5;
        Integer wi = 5;
        Assert.assertEquals(pi, wi.intValue());
        //String
        Assert.assertEquals("bola", "bola");
        //Assert.assertEquals("bola", "Bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        //Objetos
        Usuario u1 = new Usuario("Pasmo 1");
        Usuario u2 = new Usuario("Pasmo 1");
        Assert.assertEquals(u1, u2);
        //Objetos com a mesma instância.
        Usuario u3 = u2;
        Assert.assertSame(u3,u2);
        //Negado
        Assert.assertNotSame(u1,u2);
        //Objeto é nulo
        Usuario u4 = null;
        Assert.assertTrue(u4 == null);
        Assert.assertNull(u4);
        //Negado
        Assert.assertNotNull(u2);


        //Mensagem erro na comparação
        Assert.assertEquals("Primeiro valor não é igual ao segundo", 1, 3);





    }
}
