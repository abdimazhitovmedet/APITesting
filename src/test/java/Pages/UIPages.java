package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UIPages {
    WebDriver driver;
    public UIPages(){
        this.driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }






}
