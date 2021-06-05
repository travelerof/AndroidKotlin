package com.hyg

class TestAs(a : Int) : C(a) {
    override fun aa(): Boolean {
        TODO("Not yet implemented")
    }
}

/**
 * 抽象类申明
 */
abstract class C(a : Int){
    abstract fun aa() : Boolean
    fun bb(){

    }
}


