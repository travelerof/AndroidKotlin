package com.hyg.permission.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.hyg.permission.R

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class PermissionDialog : Dialog {

    private lateinit var ivTitle: ImageView
    private lateinit var tvMessage: TextView
    private var listener: OnApplyListener? = null
    constructor(context: Context, onApplyListener: OnApplyListener?) : this(
        context,
        R.style.PermissionDialog,
        onApplyListener
    )

    constructor(
        context: Context,
        themeResId: Int,
        onApplyListener: OnApplyListener?
    ) : super(context, themeResId) {
        listener = onApplyListener
        initView()
        initParams();
    }

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_permission_layout, null)
        ivTitle = view.findViewById(R.id.dialog_permission_title_iv)
        tvMessage = view.findViewById(R.id.dialog_permission_message_tv)
        val tvApply: TextView = view.findViewById(R.id.dialog_permission_apply_tv)
        val tvRefuse: TextView = view.findViewById(R.id.dialog_permission_refuse_tv)
        tvApply.setOnClickListener {//同意
            listener?.onApply(it, this)
        }
        tvRefuse.setOnClickListener {//拒绝
            listener?.onRefuse(it, this)
        }
        setContentView(view)
    }

    private fun initParams(){
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        val params =  window?.attributes
        params?.width = context.resources.displayMetrics.widthPixels - context.resources.getDimensionPixelSize(R.dimen.dimen_20)*2
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = Gravity.BOTTOM
        window?.attributes = params

    }

    fun setMessage(msg: String) {
        tvMessage.text = msg
    }

    fun setImage(imageResId: Int) {
        ivTitle.setImageResource(imageResId)
    }

}