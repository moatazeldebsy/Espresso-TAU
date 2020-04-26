package nl.testchamber.mailordercoffeeshop.data

import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("shotCounter")
fun bindShotCounter(textView: TextView, number: Int) {
    textView.setText(number.toString())
}

@BindingAdapter("showView")
fun bindShowView(view: View, showView: Boolean) {
    view.visibility = if (showView) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("seekBarChange")
fun bindSeekBarChange(view: View, listener: SeekBar.OnSeekBarChangeListener) {
    if (view is SeekBar) view.setOnSeekBarChangeListener(listener)
}

@BindingAdapter("spinnerSelected")
fun bindSpinnerSelected(view: View, listener: AdapterView.OnItemSelectedListener) {
    if (view is Spinner) view.onItemSelectedListener = listener
}

@BindingAdapter("spinnerAdapter")
fun bindSpinnerAdapter(view: View, spinnerAdapter: SpinnerAdapter) {
    if (view is Spinner) view.adapter = spinnerAdapter
}

@BindingAdapter("setDrawable")
fun bindSetDrawable(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("errorText")
fun bindErrorText(view: TextInputLayout, errorText: String?) {
    if (errorText.isNullOrBlank()) {
        view.error = null
    } else {
        view.error = errorText
    }
}

@BindingAdapter("focusChange")
fun bindFocusChange(view: TextInputEditText, listener: View.OnFocusChangeListener?) {
    view.onFocusChangeListener = listener
}

