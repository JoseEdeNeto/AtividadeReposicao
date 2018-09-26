package br.edu.utfpr.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntrarPage extends BasePage{
    
    @FindBy(xpath = "//*[@id=\"username\"]")
    WebElement usuario;
    
    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement senha;
    
    @FindBy(xpath = "//*[@id=\"login-form\"]/form/table/tbody/tr[4]/td[2]/input")
    WebElement botaoEntrar;
    
    @FindBy(xpath = "//*[@id=\"flash_error\"]")
    WebElement mensagemErro;
    
    public EntrarPage(WebDriver driver){
        super(driver);
    }
    
    public String getMensagemErro(){
        return mensagemErro.getText();
    }
    
    public String getUsuario() {
        return usuario.getAttribute("value");
    }

    public EntrarPage setUsuario(String pusuario) {
        usuario.clear();
        usuario.sendKeys(pusuario);
        return this;
    }

    public String getSenha() {
        return senha.getAttribute("value");
    }

    public EntrarPage setSenha(String psenha) {
        senha.clear();
        senha.sendKeys(psenha);
        return this;
    }
    
    public HomePage botaoEntrarValido() {
        botaoEntrar.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"content\"]/h2")).
                    getText().toLowerCase().startsWith("página inicial"));

        return new HomePage(driver);
    }
    
    public EntrarPage botaoEntrarInvalido() {
        botaoEntrar.click();
        return this;
    }
}
