package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.*;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;
    private LoginPage loginPage;
    private BookStorePage bookStorePage;
    private ShadowDOMPage ShadowDOMPage;
    private ShadowDom2Page ShadowDom2Page;

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

    public ShadowDom2Page getShadowDom2Page(){return (ShadowDom2Page == null) ? ShadowDom2Page = new ShadowDom2Page(driver) : ShadowDom2Page;}
}
