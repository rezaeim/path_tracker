package edu.ecu.cs.pirateplaces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    private val piratePlacesRepository = PiratePlacesRepository.get()
    val piratePlacesListLiveData = piratePlacesRepository.getPiratePlaces()


    fun addPiratePlace(place: PiratePlace) {
        piratePlacesRepository.addPiratePlace(place)
    }

    var coordinates = MutableLiveData<List<PiratePlace>>()

}