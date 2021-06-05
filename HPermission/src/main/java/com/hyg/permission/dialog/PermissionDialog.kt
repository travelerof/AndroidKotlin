package com.hyg.permission.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
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
    }

    fun initView() {
        var view = LayoutInflater.from(context).inflate(R.layout.dialog_permission_layout, null)
        ivTitle = view.findViewById(R.id.dialog_permission_title_iv)
        tvMessage = view.findViewById(R.id.dialog_permission_message_tv)
        var tvApply: TextView = view.findViewById(R.id.dialog_permission_apply_tv)
        var tvRefuse: TextView = view.findViewById(R.id.dialog_permission_refuse_tv)
        tvApply.setOnClickListener {
            listener?.onApply(it, this)
        }
        tvRefuse.setOnClickListener {
            listener?.onRefuse(it, this)
        }
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        setContentView(view)
    }

    fun setMessage(msg: String) {
        tvMessage.text = msg
    }

    fun setImage(imageResId: Int) {
        ivTitle.setImageResource(imageResId)
    }

}