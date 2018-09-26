package br.edu.utfpr.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjetosPage extends BasePage{
    
    @FindBy(xpath = "//*[@id=\"projects-index\"]/ul/li[1]/div/a")
    WebElement botaoAProject;       
        
    public ProjetosPage (WebDriver driver){
        super(driver);
    }
    
    public AProjectPage goToAproject() {
        botaoAProject.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> d.findElement(By.xpath("//*[@id=\"header\"]/h1")).
                    getText().toLowerCase().startsWith("a project"));

        return new AProjectPage(driver);
    }
    
}
