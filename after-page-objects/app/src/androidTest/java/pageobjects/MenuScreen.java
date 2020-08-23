package pageobjects;

import androidx.test.espresso.contrib.RecyclerViewActions;
import nl.testchamber.mailordercoffeeshop.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MenuScreen
{
    private final int menuButton = R.id.use_menu;
    private final int menuList = R.id.beverage_recycler_view;
    private final int itemDetailsTitle = R.id.beverage_detail_title;

    public void clickOnMenuButton(){
        onView(withId(menuButton)).perform(click());
    }

    public void scrollToMenuItem(String item , String title)
    {
        onView(withId(menuList))
            .perform(RecyclerViewActions.
                actionOnItem(hasDescendant(withText(item)), click()));
        onView(withId(itemDetailsTitle)).check(matches(withText(title)));
    }
}
