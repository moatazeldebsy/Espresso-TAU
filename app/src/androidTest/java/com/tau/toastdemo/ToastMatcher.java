package com.tau.toastdemo;

import android.os.IBinder;
import android.view.WindowManager;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import androidx.test.espresso.Root;

class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            return windowToken == appToken;
        }
        return false;
    }
}
