package com.asanarebel.yanbraslavski.asanarebeltask.base

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.affinitas.task.mvp.BaseContract
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by yan.braslavski on 11/14/17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.BaseView {

    companion object {
         val BUNDLE_KEY_PRESENTER = "presenter"
    }


    override fun showMessage(message: String) {
        Snackbar.make(recycler_view, message, Snackbar.LENGTH_LONG).show()
    }
    override fun showLoading() {
        loading_overlay?.let {
            it.visibility = View.VISIBLE
            it.animate().alpha(1f)
        }
    }

    override fun stopLoading() {
        loading_overlay?.let {
            it.visibility = View.VISIBLE
            it.animate().alpha(0f).withEndAction({
                it.visibility = View.GONE
            })
        }
    }

    override fun changeTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showError(errorMessage: String) {
        val snack = Snackbar.make(recycler_view, errorMessage, Snackbar.LENGTH_LONG)
        val tv = snack.view.findViewById<TextView>(android.support.design.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.RED)
        snack.show()
    }
}