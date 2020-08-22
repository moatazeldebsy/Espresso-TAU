package nl.testchamber.mailordercoffeeshop

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utils {
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isValidEmail(emailAdress: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(emailAdress).matches()

    fun checkInternetConnection(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return (connectivityManager.activeNetworkInfo != null
                && connectivityManager.activeNetworkInfo.isConnected)
    }
}
