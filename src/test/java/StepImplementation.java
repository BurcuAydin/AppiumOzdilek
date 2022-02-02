import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
public class StepImplementation<wait> extends BaseTest {

    Logger logger = LogManager.getLogger(Logs.class);

    @Step("<time> saniye kadar bekle")
    public void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    @Step("id <id> li elemente tıkla")
    public void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();
        logger.info(id + "id li element tıklandı");

    }

    @Step("id <id> li elementi bul ve <text> değerini yaz")
    public void sendkeysByid(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
    }




    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrol(String id, String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));
    }

    @Step("xpath <xpath> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrolwithxpath(String xpath, String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
    }


    @Step("Elementler <xpath> arasından rastgele bir tanesini seç ve tıkla")
    public void clickRandomelement(String xpath) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
    }

    @Step("xpath <xpath> li elemente tıkla")
    public void clickByXpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();

    }

    @Step("Sayfanın en aşağı kısmına kadar scroll et")
    public void scrollWithAction() {

        Actions actions = new Actions(appiumDriver);
        ((Actions) actions).build().perform();

        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();

    }

    }



