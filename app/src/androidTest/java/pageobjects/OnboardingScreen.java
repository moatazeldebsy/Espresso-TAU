package pageobjects;

import androidx.test.espresso.matcher.ViewMatchers;
import nl.testchamber.mailordercoffeeshop.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

public class OnboardingScreen
{
    private final int closeButton = R.id.close_button;

    public void closeOnBoardingScreen()
    {
        onView(ViewMatchers.withId(closeButton))
            .perform(click());
    }
}
