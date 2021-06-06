package com.hyg.permission.request

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hyg.permission.Permission
import com.hyg.permission.PermissionType
import com.hyg.permission.dialog.OnApplyListener
import com.hyg.permission.dialog.PermissionDialog

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class RequestFragment : Fragment(),OnApplyListener {

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

    private var mDialog: PermissionDialog? = null
    companion object {
        /**
         * 普通权限请求码
         */
        private const val PERMISSION_REQUEST_NORMAL = 1010

        /**
         * 悬浮窗权限
         */
        private const val PERMISSION_REQUEST_OVERLAY = 1011

        /**
         * 通知
         */
        private const val PERMISSION_REQUEST_NOTIFICATION = 1012

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
        mDialog?.let {
            it.dismiss()
            mDialog= null
        }
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
            callback?.onPermissionResult(response)
            detach()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (type != PermissionType.SPECIAL) {
            return
        }
        when(requestCode){
            PERMISSION_REQUEST_OVERLAY -> {
                response.permissions.add(currentPermission)
            }
        }
        requestSpecialPermission()
    }

    /**
     * 权限请求
     */
    private fun requestPermission(){
        if (type == PermissionType.SPECIAL) {
            createDialog()
            requestSpecialPermission()
            return
        }
        permissions?.let {
            requestPermissions(it.toTypedArray(), PERMISSION_REQUEST_NORMAL)
        }
    }

    /**
     * 创建dialog
     */
    private fun createDialog(){
        mDialog?:let {
            mDialog = PermissionDialog(context!!,this)
        }
    }

    /**
     * 请求特殊权限
     */
    private fun requestSpecialPermission(){
        removeCurrentPermission()
        if (permissions === null || permissions?.isEmpty() == true) {
            callback?.onPermissionResult(response)
            detach()
            return
        }
        currentPermission = permissions!![0]
        mDialog?.setMessage(Utils.getPermissionDescription(currentPermission,Utils.getAppName(context!!)))
        mDialog?.setImage(Utils.getPermissionTitleResId(currentPermission,Utils.getAppName(context!!)))
        mDialog?.show()
    }

    /**
     * 清理当前权限
     */
    private fun removeCurrentPermission(){
        if (currentPermission.isEmpty()) {
            return
        }
        permissions?.remove(currentPermission)
        currentPermission = ""
    }

    override fun onApply(v: View, dialog: PermissionDialog) {
        dialog.dismiss()
        when(currentPermission){
            Permission.WINDOW_OVERLAY -> requestOverlayPermission()
        }
    }

    override fun onRefuse(v: View, dialog: PermissionDialog) {
        dialog.dismiss()
        response.permissions.add(currentPermission)
        response.grantResults.add(PackageManager.PERMISSION_DENIED)
        requestSpecialPermission()
    }

    /**
     * 请求悬浮窗权限
     */
    private fun requestOverlayPermission(){
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        intent.data = Uri.parse("package:${context?.packageName}")
        startActivityForResult(intent, PERMISSION_REQUEST_OVERLAY)
    }
}