package pageobjects;


import android.content.Intent;

import nl.testchamber.mailordercoffeeshop.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;


public class ReviewOrderScreen {

    private final int nameTextBox = R.id.name_text_box;
    private final int customOrderNameBox = R.id.custom_order_name_box ;
    private final int mail_order_button = R.id.mail_order_button ;
    private final int beverageDetailIngredients = R.id.beverage_detail_ingredients;

    public void reviewOrder(String name , String orderName , String orderSubject)
    {
        onView(withId(nameTextBox))
            .perform(scrollTo(), typeText(name));
        onView(withId(customOrderNameBox))
            .perform(scrollTo(), typeText(orderName));
        onView(withId(mail_order_button))
            .perform(scrollTo(), click());

        intended(allOf(
            hasAction(equalTo(Intent.ACTION_SENDTO)),
            hasExtra(Intent.EXTRA_SUBJECT, orderSubject)));
    }

    public void  checkIngredients(int shots)
    {
        onView(withId(R.id.beverage_detail_ingredients))
            .check(matches(withText("Ingredients:\n"+shots+" shots of espresso\nChocolate")));
    }

}
