package com.hyg

/**
 * @Author 韩永刚
 * @Date 2021/06/03
 * @Desc 测试枚举
 */
enum class A(var tag : String) {

    ET("tag1"),AT("tag2")
}
enum class Color(color : Int){
    RED(0xff0000),
    BLACK(0x000000),
    WHITE(0xffffff)
}
enum class B{
    AAT,DDTA
}