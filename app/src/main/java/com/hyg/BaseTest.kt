package com.hyg

import android.util.Log

/**
 * @Author 韩永刚
 * @Date 2021/06/03
 * @Desc
 */
abstract class BaseTest constructor(a : Int,b : String) {
    var mA = a
    var mB = b
    fun ab(){
    }

    fun dd() : String?{
        return mB
    }
    abstract fun bc(d:Int) : Int
}

class BaseTest1(a : Int,b : String, c : Boolean):BaseTest(a,b){
    override fun bc(d: Int): Int {
        TODO("Not yet implemented")
    }
}

