package com.pawel14157.deltadroid.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.pawel14157.deltadroid.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**open Resolve activity
     *
     * @param view
     */
    fun Resolve(view: View) {
        val resolve = Intent(this, ResolveActivity::class.java)
        startActivity(resolve)
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    }

    /*open Help activity
     *
     * @param view
     */
    fun help(view: View) {
        val help = Intent(this, HelpActivity::class.java)
        startActivity(help)
    }
}



