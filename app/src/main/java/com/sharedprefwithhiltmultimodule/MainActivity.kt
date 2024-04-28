package com.sharedprefwithhiltmultimodule

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shared_pref.AppSession
import com.sharedpref_hilt.model.CustomClass
import com.sharedpref_hilt.utils.CUSTOM_DATA
import com.sharedpref_hilt.utils.USER_NAME
import com.sharedprefwithhiltmultimodule.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    @Inject
    lateinit var appSession: AppSession
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        clickEvents()

    }

    private fun clickEvents() {
        activityMainBinding.btnSaveGetData.setOnClickListener {

            saveData()
            getData()

        }

        activityMainBinding.btnSaveGetCustomData.setOnClickListener {

            saveCustomData()
            getCustomData()

        }
    }

    private fun getCustomData() {

        var myCustomData = appSession.getObjectData(
            CUSTOM_DATA,
            CustomClass::class.java
        ) as CustomClass
        activityMainBinding.tvGetSaveCustomData.setText("Product Name->" + myCustomData.productName + "\nPrice-> Rs." + myCustomData.productPrice)


    }

    private fun saveCustomData() {
        appSession.putObjectData(CUSTOM_DATA, CustomClass("Cleaner", 200) as Object)

    }

    private fun getData() {
        activityMainBinding.tvGetSaveData.setText("Hi.." + appSession.getStringData(USER_NAME, ""))
    }

    private fun saveData() {
        if (!TextUtils.isEmpty(activityMainBinding.etMyName.text.toString())) {
            appSession.putStringData(USER_NAME, activityMainBinding.etMyName.text.toString())
        }
    }

}