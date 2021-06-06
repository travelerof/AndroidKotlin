package com.hyg

//将只有引用Bundle可以用A代替
import android.Manifest
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hyg.identification.HIdentification
import com.hyg.identification.OnIdentificationCallback
import com.hyg.permission.HPermission
import com.hyg.permission.OnPermissionCallback
import com.hyg.permission.Permission
import com.hyg.permission.PermissionType
import android.os.Bundle as A

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: A?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.main_btn)
        tv.setOnClickListener {
//            request()
            auth()
        }
    }

    private fun request() {
        HPermission.with(this)
            .permission(Manifest.permission.CAMERA)
            .requestCode(100)
            .callback(object:OnPermissionCallback{
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

        HPermission.with(this)
            .permissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SEND_SMS))
            .requestCode(101)
            .callback(object:OnPermissionCallback{
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

        HPermission.with(this)
            .type(PermissionType.SPECIAL)
            .permission(Permission.WINDOW_OVERLAY)
            .requestCode(102)
            .callback(object:OnPermissionCallback{
                override fun onSucceeded(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onSuccessed=======$requestCode")
                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {
                    Log.i("test11","=====onFailed=======$requestCode")
                }
            })
            .build()
            .request()

    }


    private fun auth(){
        HIdentification.with(this)
            .callback(object :OnIdentificationCallback{
                override fun onStarted() {
                    Log.i("test111","======onStarted=========")
                }

                override fun onFailed(code: Int, msg: String) {
                    Log.i("test111","======onFailed=========$code")
                }

                override fun onSucceeded(msg: String) {
                    Log.i("test111","======onSucceeded=========$msg")
                }
                override fun onCompleted() {
                    Log.i("test111","======onCompleted=========")
                }
            })
            .build()
            .auth()
    }
}