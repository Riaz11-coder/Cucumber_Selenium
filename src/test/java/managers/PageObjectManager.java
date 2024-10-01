package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.BookStorePage;
import pageObjects.GooglePage;
import pageObjects.LoginPage;
import pageObjects.ShadowDOMPage;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;
    private LoginPage loginPage;
    private BookStorePage bookStorePage;
    private ShadowDOMPage ShadowDOMPage;

    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public BookStorePage getBookStorePage() {
        return (bookStorePage == null) ? bookStorePage = new BookStorePage(driver) : bookStorePage;
    }
    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }
    public LoginPage getLoginPage(){
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(driver) : browserUtils;
    }

    public ShadowDOMPage getShadowDOMPage()  {return (ShadowDOMPage == null ) ? ShadowDOMPage = new ShadowDOMPage(driver) : ShadowDOMPage;}
}
