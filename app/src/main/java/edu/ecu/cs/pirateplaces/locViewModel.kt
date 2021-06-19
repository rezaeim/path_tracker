package edu.ecu.cs.pirateplaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


//class locViewModel: ViewModel() {
  //  private val piratePlacesRepository = PiratePlacesRepository.get()
  // val piratePlacesListLiveData: LiveData<List<PiratePlace>> = piratePlacesRepository.getPiratePlaces()

//}

class locViewModel: ViewModel() {
    private val piratePlacesRepository = PiratePlacesRepository.get()
    val piratePlacesListLiveData: LiveData<List<PiratePlace>> = piratePlacesRepository.getPiratePlaces()
}

