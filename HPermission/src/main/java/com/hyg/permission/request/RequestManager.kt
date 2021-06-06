package com.hyg.permission.request

import java.util.concurrent.LinkedBlockingQueue

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
object RequestManager : IExecuteListener {
    private var mRequests: LinkedBlockingQueue<IRequest> = LinkedBlockingQueue()
    private var isExecute = false
    fun add(request: IRequest): Boolean {
        request.addExecuteListener(this)
        return mRequests.offer(request)
    }

    fun hasNext(): Boolean = mRequests.isNotEmpty()

    fun next(): IRequest = mRequests.poll()!!

    override fun onComplete() {
        isExecute = false
        request()
    }

    fun request() {
        if (isExecute || !hasNext()) {
            return
        }
        isExecute = true
        next().request()
    }
}