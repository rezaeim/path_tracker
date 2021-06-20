package edu.ecu.cs.pirateplaces

import android.text.format.DateFormat.getTimeFormat
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import java.text.DateFormat
import kotlin.time.hours
import kotlin.time.minutes

private const val TAG = "ListItemViewModel"

class ListItemViewModel(private val dateFormat: DateFormat, private val timeFormat: DateFormat) {
    var _place = PiratePlace()

    var place:PiratePlace
        set(place: PiratePlace) {
            _place = place
            _name.value = place.name
            _visited.value = place.visitedWith
            val dateString = dateFormat.format(place.lastVisited)
            _date.value = dateString
            val timeString = timeFormat.format(place.lastVisited)
            _time.value = timeString
        }
        get() {
            return _place

        }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    private val _visited = MutableLiveData<String>()
    val visited : LiveData<String>
        get() = _visited

    private val _date = MutableLiveData<String>()
    val date : LiveData<String>
        get() = _date

    private val _time = MutableLiveData<String>()
    val time : LiveData<String>
        get() = _time


}