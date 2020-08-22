package nl.testchamber.apiservice.data

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

data class BeverageMenuItem(val name: String, val volume: Int, val ingredients: List<Ingredient>, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.createTypedArrayList(Ingredient),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(volume)
        parcel.writeTypedList(ingredients)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BeverageMenuItem> {
        override fun createFromParcel(parcel: Parcel): BeverageMenuItem {
            return BeverageMenuItem(parcel)
        }

        override fun newArray(size: Int): Array<BeverageMenuItem?> {
            return arrayOfNulls(size)
        }

        val PARCEL_NAME = "beverageMenuItem"
    }
}

