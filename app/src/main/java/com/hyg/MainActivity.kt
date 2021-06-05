package com.hyg

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.hyg.log.HLog
import com.hyg.permission.HPermission
import com.hyg.permission.OnPermissionCallback
//将只有引用Bundle可以用A代替
import android.os.Bundle as A

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: A?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HPermission.with(this)
            .requestCode(100)
            .callback(object : OnPermissionCallback{
                override fun onSuccessed(requestCode: Int, permissions: Array<String>) {

                }

                override fun onFailed(requestCode: Int, permissions: Array<String>) {

                }
            })
            .permission("")
            .build()
            .request()
    }
}