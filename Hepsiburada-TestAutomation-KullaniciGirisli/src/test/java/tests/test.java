package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.hepsiburada_page;
import utilities.ConfigReader;
import utilities.Driver;
import java.util.Set;

public class test {
    static WebDriver driver= Driver.getDriver();
    final static Logger logger = Logger.getLogger(test.class);
    hepsiburada_page hepsiburada_page = new hepsiburada_page();
    @BeforeClass
    public static void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("hepsiburadaUrl"));
        logger.info("Hepsiburada sayfasına gidildi.");
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Test (priority = 1)
    public void get_page() {

        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("assert_mainpage")));
        logger.info("Ana sayfada olduğu doğrulandı.");

        Actions actions = new Actions(driver);

        actions.clickAndHold(hepsiburada_page.girisyapmenu).perform();
        logger.info("'Giriş Yap' elementi bulunarak imleç elementin üzerine getirildi.");

        hepsiburada_page.girisyapbutton.click();
        logger.info("Giriş Yap elementine tıklandı.");
    }

    @Test (priority = 2, dependsOnMethods = "get_page")
    public void login() {
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("assert_loginpage")));
        logger.info("İstenilen sayfada olduğu doğrulandı.");

        hepsiburada_page.cookies.click();
        logger.info("Çerezler kabul edildi.");

        hepsiburada_page.emailbox.sendKeys(ConfigReader.getProperty("email"));
        hepsiburada_page.emailgiris.submit();

        hepsiburada_page.passwordbox.sendKeys(ConfigReader.getProperty("password"));
        hepsiburada_page.passwordgiris.click();
        logger.info("Mail ve şifre girişleri yapılarak 'Giriş Yap' butonuna tıklandı.");
    }

    @Test (priority = 3, dependsOnMethods = "login")
    public void searchbox() throws InterruptedException {

        Assert.assertFalse(hepsiburada_page.assertloginpage.getText().contains("üye ol"));
        logger.info("Kullanıcı girişinin yapıldığı doğrulandı.");

        hepsiburada_page.searchbox.sendKeys(ConfigReader.getProperty("searching_product"));
        logger.info("Arama çubuğuna 'Telefon' kelimesi yazdırıldı.");

        hepsiburada_page.searching_button.click();
        logger.info("Arama tuşuna basıldı");

        Thread.sleep(5000);
    }

    @Test (priority = 4, dependsOnMethods = "searchbox")
    public void product_choose() {
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("assert_searchingpage")));
        logger.info("Arama sonucu sayfasında olduğu doğrulandı.");

        hepsiburada_page.first_product.click();
        logger.info("İlk ürüne tıklandı.");

        Set<String>windowAllWindows = driver.getWindowHandles();

        for (String window:windowAllWindows) {
            driver.switchTo().window(window);
        }
        logger.info("Driver'ın yeni sekmeye geçişi yapıldı.");

        hepsiburada_page.add_to_cart.click();
        logger.info("Ürün sepete eklendi.");

        Assert.assertTrue(hepsiburada_page.product_information.getText().contains(hepsiburada_page.click_of_product_text.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynısı olduğu doğrulandı.");
    }

    @Test (priority = 5, dependsOnMethods = "product_choose")
    public void add_other_product() throws InterruptedException {
        Thread.sleep(5000);

        hepsiburada_page.keep_shopping.click();
        logger.info("'Alışverise devam et.' elementine tıklandı.");

        Thread.sleep(5000);

        hepsiburada_page.first_product.click();
        Thread.sleep(5000);
        logger.info("Arama sonuçlarında bulunan ilk ürüne tıklandı.");

        Set<String> windowAllWindows = driver.getWindowHandles();

        for (String window : windowAllWindows) {
            driver.switchTo().window(window);
        }
        logger.info("Açılan 3. sekmeye driver geçişi yapıldı.");

        hepsiburada_page.other_product.click();
        logger.info("Başka bir satıcıdan aynı ürün sepete eklendi..");

        Thread.sleep(5000);

        Assert.assertTrue(hepsiburada_page.other_product_information.getText().contains(hepsiburada_page.click_of_other_product_text.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynısı olduğu doğrulandı.");

    }

    @Test(priority = 6, dependsOnMethods = "add_other_product")
    public void last_product_add() throws InterruptedException {

        hepsiburada_page.keep_shopping.click();
        logger.info("'Alışverişe Devam Et' elementine tıklandı.");
        Thread.sleep(5000);

        hepsiburada_page.first_product.click();
        logger.info("Arama sonuçlarındaki ilk ürüne tıklandı.");

        Set<String>windowAllWindows = driver.getWindowHandles();
        for (String window:windowAllWindows) {
            driver.switchTo().window(window);
        }
        hepsiburada_page.last_product_adding.click();
        logger.info("Farklı bir satıcıdan daha aynı ürün sepete eklendi.");

        Thread.sleep(5000);

        Assert.assertTrue(hepsiburada_page.product_information.getText().contains(hepsiburada_page.other_product_information.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynı olduğu kontrol edildi.");

        hepsiburada_page.go_cart.click();
        logger.info("Sepetim sayfasına gitmek için 'Sepetim' elementine tıklandı.");
    }
}

