package com.example.karshsoni.contacturidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val YOUR_FRAGMENT_STRING_TAG = "FragMain"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentPopped = fragmentManager
                .popBackStackImmediate(YOUR_FRAGMENT_STRING_TAG, 0)
        if (!fragmentPopped && fragmentManager.findFragmentByTag(YOUR_FRAGMENT_STRING_TAG) == null){
            val manager = fragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.mainFrag, FragmentHome(), YOUR_FRAGMENT_STRING_TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

}
