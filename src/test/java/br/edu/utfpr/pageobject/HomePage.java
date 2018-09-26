package br.edu.utfpr.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    
    @FindBy(xpath = "//*[@id=\"account\"]/ul/li[1]/a")
    WebElement botaoEntrar;
    
    @FindBy(xpath = "//*[@id=\"content\"]/h2")
    WebElement titulo;
    
    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[2]/a")
    WebElement botaoProjetos;
    
    @FindBy(xpath = "//*[@id=\"account\"]/ul/li[2]/a")
    WebElement botaoSair;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public String getTitle() {
        return titulo.getText();
    }
    
    public String getBotaoEntrar(){
        return botaoEntrar.getText();
    }
    
    public EntrarPage goToEntrar() {
        botaoEntrar.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"login-form\"]/form/table/tbody/tr[1]/td[1]/label")).
                getText().toLowerCase().startsWith("usuário"));
        return new EntrarPage(driver);
    }
    
    public ProjetosPage goToProjetos(){
        botaoProjetos.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"content\"]/h2")).
                getText().toLowerCase().startsWith("projetos"));
        return new ProjetosPage(driver);
    }
    
    public HomePage botaoSair(){
        botaoSair.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"account\"]/ul/li[1]/a")).
                getText().toLowerCase().startsWith("entrar"));
        return new HomePage(driver);
    }

}
