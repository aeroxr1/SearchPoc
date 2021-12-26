package com.aeroxr1.searchpoc.ui.main


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MainViewModel() : ViewModel() {

    val listener = Listener()
    val oldRepo = OldRepo(listener)

    val searchString:MutableStateFlow<String> = MutableStateFlow("")

    val listOfPerson = searchString.flatMapLatest { query ->
        listener.listPerson.mapLatest { listOfPerson -> filterTest(listOfPerson, query) }
    }.flowOn(Dispatchers.Default)


    private fun filterTest(listOfPerson:List<Person>, queryString:String):List<Person>{
        val result = listOfPerson.filter { queryString.isEmpty() || it.name.startsWith(queryString, true) }
        return result
    }


    fun pressGenerate1(){
        oldRepo.generateRandomData()
    }

    fun pressGenerate2(){
        oldRepo.generateRandomData2()
    }


}