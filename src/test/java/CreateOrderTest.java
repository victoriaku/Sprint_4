import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.CreateOrderPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest{

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public CreateOrderTest(String name, String surname, String address, String metroStation, String phoneNumber,
                           String deliveryDate, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] orderDetails() {
        return new Object[][]{
                {"Иосиф", "Бродский", "Ярославль", "Маяковская", "89998887766",
                        LocalDate.now().plusDays(12).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(),
                        "трое суток", "black", "Звоните"},
                {"Анна", "Иванова", "проспект Ленина 1", "Тверская", "+79998887766",
                        LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(),
                        "шестеро суток", "grey", "Не звоните"}
        };
    }

    @Test
    public void createOrderFromHeaderButtonSuccess(){
        mainPage.clickHeaderCreateOrderButton();
        createOrder();
        assertTrue("Должно появиться окно \"Заказ оформлен\"", createOrderPage.hasOrderPlacedText());
    }

    @Test
    public void createOrderFromBottomButtonSuccess(){
        mainPage.clickBottomCreateOrderButton();
        createOrder();
        assertTrue("Должно появиться окно \"Заказ оформлен\"", createOrderPage.hasOrderPlacedText());
    }

    public void createOrder(){
        createOrderPage = new CreateOrderPage(driver);
        createOrderPage.fillFormAboutClient(name, surname, address, metroStation, phoneNumber);
        createOrderPage.clickNextButton();

        createOrderPage.fillFormAboutRent(deliveryDate, rentalPeriod, color, comment);
        createOrderPage.clickCreateOrderButton();
        createOrderPage.clickConfirmOrderButton();
    }
}
