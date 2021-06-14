package edu.ecu.cs.pirateplaces

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import java.text.DateFormat

private const val TAG = "ListItemViewModel"

class ListItemViewModel(private val dateFormat: DateFormat, private val timeFormat: DateFormat) {
    var _place = PiratePlace()

    var place:PiratePlace
        set(place: PiratePlace) {
            _place = place
            _name.value = place.name
        }
        get() {
            return _place
        }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name
}