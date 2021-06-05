package com.hyg.permission

import android.content.Context
import androidx.fragment.app.Fragment
import com.hyg.permission.request.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
class HPermission {

    companion object {
        fun with(fragment: Fragment): PermissionBuilder {
            return fragment.activity?.let { with(it) }!!
        }

        fun with(context: Context): PermissionBuilder = PermissionBuilder(context)
    }

    class PermissionBuilder constructor(context: Context) {

        private var mContext: Context = context
        private var options: RequestOptions = RequestOptions()

        fun type(@PermissionType type: Int): PermissionBuilder {
            options.type = type
            return this
        }

        fun requestCode(requestCode: Int): PermissionBuilder {
            options.requestCode = requestCode
            return this
        }

        fun permission(permission: String, permissions: Array<String>? = null): PermissionBuilder {
            options.permissions.add(permission)
            //permissions不为空时设置
            permissions?.let { options.permissions.addAll(it)}
            return this
        }

        fun permissions(permissions: Array<String>): PermissionBuilder {
            options.permissions.addAll(permissions)
            return this
        }

        fun callback(callback: OnPermissionCallback): PermissionBuilder {
            options.callback = callback
            return this
        }

        fun build(): RequestManager{
            var request: IRequest = if (options.type == PermissionType.SPECIAL) SpecialRequest(mContext,options) else Request(mContext,options)
            RequestManager.add(request)
            return RequestManager
        }

    }
}