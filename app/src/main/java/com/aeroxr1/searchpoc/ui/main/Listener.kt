package com.aeroxr1.searchpoc.ui.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class Person(val id:Int, val name:String)

interface MockInterface{
    fun startProcess()
    fun personAdded(person: Person)
    fun endProcess()
}

class Listener:MockInterface {

    private val _listPerson = MutableStateFlow<List<Person>>(emptyList())
    val listPerson = _listPerson.asStateFlow()
    private var tmpPerson:ArrayList<Person> = ArrayList()

    override fun startProcess() {
        _listPerson.value = emptyList<Person>()
    }

    override fun personAdded(person: Person) {
        tmpPerson.add(person)
    }

    override fun endProcess() {
        _listPerson.value = tmpPerson
        tmpPerson = ArrayList()
    }

}

class OldRepo(private val listener: MockInterface){

    fun generateRandomData(){
        Thread{
            listener.startProcess()
            for (i in 1..5){
                listener.personAdded(Person(Random(i).nextInt(), "Mario ${Random(i).nextInt()}"))
            }
            for (i in 1..5){
                listener.personAdded(Person(Random(i).nextInt(), "Luca ${Random(i).nextInt()}"))
            }
            listener.endProcess()
        }.start()
    }

    fun generateRandomData2(){
        Thread{
            listener.startProcess()
            for (i in 1..10){
                listener.personAdded(Person(Random(i).nextInt(), "Pluto ${Random(i).nextInt()}"))
            }
            listener.endProcess()
        }.start()
    }

    fun generateRandomData3(){
        Thread{
            listener.startProcess()
            for (i in 1..10){
                listener.personAdded(Person(Random(i).nextInt(), "Gabriele ${Random(i).nextInt()}"))
            }
            listener.endProcess()
        }.start()
    }

}