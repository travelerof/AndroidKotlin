package com.hyg.video.ui.manager

import com.hyg.video.ui.controller.IController

/**
 * @Author 韩永刚
 * @Date 2021/07/29
 * @Desc 控制器管理类
 */
internal class ControllerManagerImpl : IControllerManager {

    private val controllers = mutableMapOf<String, IController>()

    override fun containsKey(key: String): Boolean = controllers.containsKey(key)

    override fun add(controller: IController) {
        if (containsKey(controller)) {
            return
        }
        controllers[controller.getTag()] = controller
    }

    override fun replace(controller: IController) {
        if (containsKey(controller.getTag())) {
//            controllers.replace(controller.getTag(),controller)
        }
    }

    override fun remove(key: String) {
        if (containsKey(key)) {
            controllers.remove(key)
        }
    }

    override fun remove(controller: IController) {
        remove(controller.getTag())
    }

    override fun get(key: String): IController? = controllers[key]

    override fun getSize(): Int = controllers.size

    override fun isEmpty(): Boolean = controllers.isEmpty()

    override fun clear() {
        controllers.forEach { (key, value) -> value.release() }
        controllers.clear()
    }

    override fun dispense(event: Int) {
        controllers.forEach { (key, value) -> value.perform(event) }
    }

    override fun filterAnimators(animator: Boolean): MutableList<IController> {
        val animatorControllers = mutableListOf<IController>()
        if (controllers.isEmpty()) {
            return animatorControllers
        }
        controllers.forEach { (key, value) ->
            if (value.isAnimator()) {
                animatorControllers.add(value)
            }
        }
        return animatorControllers
    }
}