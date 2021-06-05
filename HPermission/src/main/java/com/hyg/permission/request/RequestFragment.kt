package com.hyg.permission.request

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hyg.permission.PermissionType

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class RequestFragment : Fragment() {

    @PermissionType
    private var type: Int?= PermissionType.NORMAL
    private var requstCode: Int? = 100
    private var permissions: ArrayList<String>? = null

    /**
     * 请求结果
     */
    private var callback: IRequestCallback? = null
    /**
     * 结果
     */
    private var response: PermissionResponse = PermissionResponse()

    private var currentPermission: String = ""

    companion object {
        /**
         * 普通权限请求码
         */
        private const val PERMISSION_REQUEST_NORMAL = 1010

        /**
         * 特殊权限请求码
         */
        private const val PERMISSION_REQUEST_SPECIAL = 1011

        private const val TAG = "RequestFragment"
        private const val PERMISSION_TYPE_KEY = "permission_type_key"
        private const val PERMISSION_REQUEST_CODE_KEY = "permission_request_code_key"
        private const val PERMISSIONS_KEY = "permissions_key"
        fun newFragment(
            @PermissionType type: Int,
            requestCode: Int,
            permissions: ArrayList<String>
        ): RequestFragment {
            val fragment = RequestFragment()
            val bundle = Bundle()
            bundle.putInt(PERMISSION_TYPE_KEY, type)
            bundle.putInt(PERMISSION_REQUEST_CODE_KEY, requestCode)
            bundle.putStringArrayList(PERMISSIONS_KEY, permissions)
            fragment.arguments = bundle
            return fragment
        }
    }

    fun attach(activity: FragmentActivity,callback: IRequestCallback){
        this.callback = callback
        activity.supportFragmentManager.beginTransaction().add(this, TAG).commitAllowingStateLoss()
    }

    private fun detach(){
        callback = null
        activity!!.supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        type = arguments?.getInt(PERMISSION_TYPE_KEY)
        requstCode = arguments?.getInt(PERMISSION_REQUEST_CODE_KEY)
        permissions = arguments?.getStringArrayList(PERMISSIONS_KEY)
        response.requestCode = requstCode!!
        permissions?.let {
            requestPermission()
        }?: run {
            response.code = -1
            callback?.onPermissionResult(response)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_NORMAL) {
            response.permissions.addAll(permissions)
            response.grantResults.addAll(grantResults.toTypedArray())
            detach()
        }
    }
    private fun requestPermission(){
        if (type == PermissionType.SPECIAL) {
            requestSpecialPermission()
            return
        }
        permissions?.let {
            requestPermissions(it.toTypedArray(), PERMISSION_REQUEST_NORMAL)
        }
    }

    private fun requestSpecialPermission(){
        removeCurrentPermission()
        permissions?.let {
            if (it.isEmpty()) {
                callback?.onPermissionResult(response)
            }else{

            }
        }
    }

    private fun removeCurrentPermission(){
        if (currentPermission.isEmpty()) {
            return
        }
        permissions?.remove(currentPermission)
        currentPermission = ""
    }
}