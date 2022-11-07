package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class hepsiburada_page {
    public hepsiburada_page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[1]/div/div[2]/div/button[2]")
    public WebElement cookies;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[3]/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[2]/input")
    public WebElement searchbox;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[3]/div[5]/div/div/div/div/div[2]/div[2]/div/div/div/div/div/div/div[2]")
    public WebElement searching_button;

    @FindBy(xpath = "/html/body/div[3]/main/div[2]/div/div[6]/div[2]/div[2]/div[4]/div/div/div/div/div/div/ul/li[1]")
    public WebElement first_product;

    @FindBy(xpath= "//*[@id='addToCart']")
    public WebElement add_to_cart;

    @FindBy(xpath = "/html/body/div[3]/main/div[2]/div/div[7]/div/div[2]/div/div[3]/div/div/div/div/div/div/ul/li[1]")
    public WebElement same_product_keep_shopping;

    @FindBy(xpath= "/html/body/div[7]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[2]/button[2]")
    public WebElement keep_shopping;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/section[1]/div[3]/div/div[4]/div[2]/div[3]/div/div[2]/table/tbody/tr[1]/td[3]/div/form/button")
    public WebElement other_product;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[2]/button[1]")
    public WebElement addingtoCart;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/section[1]/div[3]/div/div[4]/div[1]/header/h1")
    public WebElement text_of_click_product;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/section[1]/div[3]/div/div[4]/div[1]/header/h1")
    public WebElement text_of_added_product;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/h6")
    public WebElement text_of_added_other_product;

    @FindBy(xpath = "/html/body/div[3]/main/div[2]/div/div[7]/div/div[2]/div/div[3]/div/div/div/div/div/div/ul/li[1]")
    public WebElement last_product;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/section[1]/div[3]/div/div[4]/div[2]/div[3]/div/div[2]/table/tbody/tr[2]/td[3]/div/form/button")
    public WebElement last_product_adding;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/h6")
    public WebElement last_product_text;
}
