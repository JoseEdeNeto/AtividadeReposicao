package br.edu.utfpr.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AProjectPage extends BasePage{
    
    @FindBy(xpath = "//*[@id=\"content\"]/h2")
    WebElement visaoGeral;

    public AProjectPage(WebDriver driver) {
        super(driver);
    }
    
    public String getVisaoGeral(){
        return visaoGeral.getText();
    }

}
