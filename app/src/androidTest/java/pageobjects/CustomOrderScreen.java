package pageobjects;
import nl.testchamber.mailordercoffeeshop.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CustomOrderScreen {

    private final String increaseButton = "+";
    private final String decreaseButton = "-" ;
    private final int chocolateCheck = R.id.chocolate;
    private final int review_order_button = R.string.review_order_button;

    public void customizeYourOrder(int itemNumber)
    {
        for (int i = 0; i < itemNumber; i++) {
            onView(withText(increaseButton)).perform(click());
        }
        onView(withText(decreaseButton))
            .perform(click());
        onView(withId(chocolateCheck))
            .perform(click());
        onView(withText(review_order_button))
            .perform(click());
    }
}
