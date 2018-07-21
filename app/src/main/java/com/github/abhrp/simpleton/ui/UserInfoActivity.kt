package com.github.abhrp.simpleton.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.abhrp.simpleton.R
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        setSupportActionBar(toolbar)
    }
}
