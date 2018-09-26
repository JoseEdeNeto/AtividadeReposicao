package br.edu.utfpr.reposicao;

import br.edu.utfpr.pageobject.AProjectPage;
import br.edu.utfpr.pageobject.EntrarPage;
import br.edu.utfpr.pageobject.HomePage;
import br.edu.utfpr.pageobject.ProjetosPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedmineTest {

    private static int scId = 0;

    WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1920x1080");
        chromeOptions.addArguments("start-maximized");
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }
    
    @After
    public void after() {
        driver.close();
    }

    @Test
    public void entrarValido(){
        driver.get("http://demo.redmine.org/");
        HomePage homePage = new HomePage(driver);
        
        EntrarPage entrarPage = homePage.goToEntrar();
        String oUsuario = entrarPage.getUsuario();
        assertEquals("", oUsuario);
        String oSenha = entrarPage.getSenha();
        assertEquals("", oSenha);
        
        homePage = entrarPage.setUsuario(oUsuario + "UsuarioTesteTopicos").setSenha(oSenha + "senhateste").botaoEntrarValido();
        
        assertEquals("Página inicial", homePage.getTitle());
        
    }
    
    @Test
    public void entrarInvalido(){
        driver.get("http://demo.redmine.org/");
        HomePage homePage = new HomePage(driver);
        
        EntrarPage entrarPage = homePage.goToEntrar();
        String oUsuario = entrarPage.getUsuario();
        assertEquals("", oUsuario);
        String oSenha = entrarPage.getSenha();
        assertEquals("", oSenha);
        
        entrarPage.setUsuario(oUsuario + "Usuario").setSenha(oSenha + "senha").botaoEntrarInvalido();
        
        assertEquals("Usuário ou senha inválido.", entrarPage.getMensagemErro());
        
    }
    
    public void sair(){
        driver.get("http://demo.redmine.org/");
        HomePage homePage = new HomePage(driver);
        
        EntrarPage entrarPage = homePage.goToEntrar();
        String oUsuario = entrarPage.getUsuario();
        assertEquals("", oUsuario);
        String oSenha = entrarPage.getSenha();
        assertEquals("", oSenha);
        
        entrarPage.setUsuario(oUsuario + "Usuario").setSenha(oSenha + "senha").botaoEntrarInvalido();
        
        homePage = homePage.botaoSair();
        
        assertEquals("Entrar", homePage.getBotaoEntrar());
    }
    
    @Test
    public void aProject(){
        driver.get("http://demo.redmine.org/");
        HomePage homePage = new HomePage(driver);
        
        ProjetosPage projetosPage = homePage.goToProjetos();
        AProjectPage aProjectPage = projetosPage.goToAproject();
        
        assertEquals("Visão geral", aProjectPage.getVisaoGeral());
    }
}