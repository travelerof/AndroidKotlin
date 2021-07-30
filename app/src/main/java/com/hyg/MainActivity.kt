package com.hyg

//将只有引用Bundle可以用A代替
import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hyg.dialog.HDialog
import com.hyg.identification.HIdentification
import com.hyg.identification.OnIdentificationCallback
import com.hyg.permission.HPermission
import com.hyg.permission.OnPermissionCallback
import com.hyg.permission.Permission
import com.hyg.permission.PermissionType
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle as A

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: A?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val tv = findViewById<TextView>(R.id.main_btn)
        main_btn.setOnClickListener {
            startActivity(Intent(this,VideoActivity::class.java))
            MyTest(this,12).log()
        }
//        tv.setOnClickListener {
////            request()
////            auth()
//            dialog()
//        }
    }

    private fun request() {
        HPermission.with(this)
            .permission(Manifest.permission.CAMERA)
            .requestCode(100)
            .callback(object : OnPermissionCallback {
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

        HPermission.with(this)
            .permissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SEND_SMS
                )
            )
            .requestCode(101)
            .callback(object : OnPermissionCallback {
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

        HPermission.with(this)
            .type(PermissionType.SPECIAL)
            .permission(Permission.WINDOW_OVERLAY)
            .requestCode(102)
            .callback(object : OnPermissionCallback {
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11", "=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

    }


    private fun auth() {
        HIdentification.with(this)
            .callback(object : OnIdentificationCallback {
                override fun onStarted() {
                    Log.i("test111", "======onStarted=========")
                }

                override fun onFailed(code: Int, msg: String) {
                    Log.i("test111", "======onFailed=========$code")
                }

                override fun onSucceeded(msg: String) {
                    Log.i("test111", "======onSucceeded=========$msg")
                }

                override fun onCompleted() {
                    Log.i("test111", "======onCompleted=========")
                }
            })
            .build()
            .auth()
    }

    private fun dialog(){
        HDialog.Builder(this)
//            .setGravity(Gravity.BOTTOM)
//            .setWindowAnimationStyleId(R.style.HDialog_Bottom_Anim)
            .build()
            .show()
    }
}