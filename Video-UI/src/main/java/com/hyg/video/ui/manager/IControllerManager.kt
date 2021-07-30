package com.hyg.video.ui.manager

import com.hyg.video.ui.controller.IController

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc
 */
interface IControllerManager {

    /**
     * 是否包含对应控制器
     *
     * @param key String
     * @return Boolean
     */
    fun containsKey(key: String): Boolean

    /**
     * 是否包含对应控制器
     *
     * @param controller IController
     * @return Boolean
     */
    fun containsKey(controller: IController): Boolean = containsKey(controller.getTag())

    /**
     * 添加控制器
     *
     * @param controller IController
     */
    fun add(controller: IController)

    /**
     * 替换对应控制器
     *
     * @param controller IController
     */
    fun replace(controller: IController)
    /**
     * 移除对应控制器
     *
     * @param key String
     */
    fun remove(key: String)

    /**
     * 移除对应控制器
     *
     * @param controller IController
     */
    fun remove(controller: IController)

    /**
     * 获取对应控制器
     *
     * @param key String
     * @return IController?
     */
    fun get(key: String): IController?

    /**
     * 获取控制器数量
     *
     * @return Int
     */
    fun getSize(): Int

    /**
     * 控制器集合是否为空
     *
     * @return Boolean
     */
    fun isEmpty(): Boolean

    /**
     * 释放，并清空控制器
     */
    fun clear()

    /**
     * 给控制器分发操作码
     *
     * @param event Int
     */
    fun dispense(event: Int)

    /**
     * 是否执行动画查询控制器列表
     *
     * @param animator Boolean
     * @return ArrayList<IController>
     */
    fun filterAnimators(animator: Boolean): MutableList<IController>
}