package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage {

    private WebDriver driver;

    //Изображение "Такого заказа нет"
    public static final By ORDER_NOT_FOUND_IMAGE = By.xpath(".//img[@alt='Not found']");

    public OrderStatusPage(WebDriver driver){
        this.driver = driver;
    }

    //Отображается ли изображение "Такого заказа нет"
    public boolean hasOrderNotFoundImage(){
        return !driver.findElements(OrderStatusPage.ORDER_NOT_FOUND_IMAGE).isEmpty();
    }
}

