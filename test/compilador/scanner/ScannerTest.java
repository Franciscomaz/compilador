/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.scanner;

import compilador.token.Token;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chicom
 */
public class ScannerTest {
    
    public ScannerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetLiteral() throws Exception {
        String texto = "'teste literal'";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals("teste literal", result.pop().getLexema());
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAtribuidor() throws Exception {
        String texto = ":=";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals(38, result.pop().getCodigo());
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPalavraReservada() throws Exception {
        String texto = "Program";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals(1, result.pop().getCodigo());
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInteiro() throws Exception {
        String texto = "42\n1412a";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals("1231421412", result.pop().getLexema());
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetIdentificador() throws Exception {
        String texto = "_test123";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals("_test123", result.pop().getLexema());
    }

    /**
     * Test of geTokens method, of class Scanner.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOperadoresRelacionais() throws Exception {
        String texto = ">";
        Scanner instance = new Scanner(new Leitor(texto));
        Stack<Token> result = instance.geTokens();
        assertEquals(41, result.pop().getCodigo());
    }
}