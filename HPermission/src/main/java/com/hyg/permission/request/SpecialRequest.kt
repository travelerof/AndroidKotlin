package com.hyg.permission.request

import android.content.Context
import java.util.*

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class SpecialRequest(context: Context,options: RequestOptions):HRequest(context,options) {
    override fun checkedPermission(permissions: TreeSet<String>): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getTag(): String {
        TODO("Not yet implemented")
    }


}