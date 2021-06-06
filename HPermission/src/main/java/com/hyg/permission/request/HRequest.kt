package com.hyg.permission.request

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
abstract class HRequest(context: Context, options: RequestOptions) : IRequest,IRequestCallback {
    private val mOptions: RequestOptions = options
    private val mContext: Context = context
    private var executeListener: IExecuteListener? = null

    abstract fun checkedPermission(permissions: TreeSet<String>): ArrayList<String>

    fun getContext(): Context = mContext

    override fun request() {
        val permissions = checkedPermission(mOptions.permissions)
        if (permissions.isEmpty()) {//没有需要申请的权限
            mOptions.callback?.onSucceeded(mOptions.requestCode,mOptions.permissions.toTypedArray())
            executeListener?.onComplete()
            return
        }
        attach(permissions)
    }

    private fun attach(permissions: ArrayList<String>){

        val activity = getActivity() ?: return
        val fragment = RequestFragment.newFragment(mOptions.type,mOptions.requestCode,permissions)
        fragment.attach(activity,this)
    }

    override fun addExecuteListener(listener: IExecuteListener) {
        executeListener = listener
    }

    /**
     * 通过context获取activity
     */
    private fun getActivity(): FragmentActivity? {
        var context: Context? = mContext
        while (context != null) {
            when {
                context is FragmentActivity -> return context
                context is ContextWrapper -> context = context.baseContext
            }
        }
        return null
    }

    /**
     * 权限请求结果
     */
    override fun onPermissionResult(result: PermissionResponse) {
        val succeeded = ArrayList<String>()
        val failed = ArrayList<String>()

        val size = result.grantResults.size
        if (size != result.permissions.size) {
            mOptions.callback?.onFailed(result.requestCode,result.permissions.toTypedArray())
            executeListener?.onComplete()
            return
        }
        for (index in 0 until size){
            if (result.grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                succeeded.add(result.permissions[index])
            }else{
                failed.add(result.permissions[index])
            }
        }
        if (failed.isNotEmpty()) {
            mOptions.callback?.onFailed(result.requestCode,failed.toTypedArray())
            executeListener?.onComplete()
            return
        }
        mOptions.callback?.onSucceeded(result.requestCode,succeeded.toTypedArray())
        executeListener?.onComplete()
    }

}