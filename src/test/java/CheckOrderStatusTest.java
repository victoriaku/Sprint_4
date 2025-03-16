import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderStatusPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CheckOrderStatusTest extends BaseTest{

    private final String orderNumber;

    public CheckOrderStatusTest(String orderNumber){
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[] orderNumbers() {
        return new Object[]{ "123", "456" };
    }

    @Test
    public void invalidOrderNumberShowsError(){
        mainPage.clickOrderStatusButton();
        mainPage.setOrderNumber(orderNumber);
        mainPage.clickGoButton();
        orderStatusPage = new OrderStatusPage(driver);
        assertTrue("Картинки с ошибкой нет", orderStatusPage.hasOrderNotFoundImage());
    }
}
