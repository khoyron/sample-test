package com.sample.jsonplaceholderapp

import android.os.Build
import android.util.Log
import android.os.Bundle
import android.view.View
import android.os.Handler
import android.app.Activity
import android.view.Window
import android.widget.Toast
import android.content.Intent
import android.content.Context
import android.view.WindowManager
import android.app.ProgressDialog
import android.app.ActivityOptions
import androidx.fragment.app.Fragment
import android.net.ConnectivityManager
import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity() {

    protected lateinit var pDialog: ProgressDialog
    protected var statusInternet : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getLayout())
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)

        OnMain()
    }

    fun withStatusBar() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }

    abstract fun getLayout(): Int
    abstract fun OnMain()

    protected fun showDialog(Mdialog: String) {
        if (!pDialog.isShowing)
            pDialog.setMessage(Mdialog)
        pDialog.show()
    }

    protected fun hideDialog() {
        if (pDialog.isShowing)
            pDialog.dismiss()
    }

    fun setLog(message: String){
        if(BuildConfig.DEBUG){
            Log.e("Test",message)
        }
    }



    fun setToast(message: String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
    }

    fun closeApplication(message: String){
        AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("YES") { _, _ ->
                    val exit = Intent(Intent.ACTION_MAIN)

                    exit.addCategory(Intent.CATEGORY_HOME)

                    exit.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    startActivity(exit)
                }
                .setNegativeButton("NO", null)
                .show()
    }

    fun gotoActivity(clas : Class<*>?){
        startActivity(Intent(this,clas))
    }

    fun gotoActivityResult(clas : Class<*>?,code:Int){
        startActivityForResult(Intent(this,clas),code)
    }

    fun gotoActivityResultWithBundle(clas : Class<*>?,bundle: Bundle,code:Int){
        val intent = Intent(this,clas)
        intent.putExtra("data",bundle)
        startActivityForResult(intent,code)
    }


    fun gotoActivityWithBundle(clas : Class<*>?,bundle: Bundle){
        val intent = Intent(this,clas)
        intent.putExtra("data",bundle)
        startActivity(intent)
    }

    fun gotoActivityWithBundleUsingTransition(view: View, bundle:Bundle, clas : Class<*>?) {
        val intent = Intent(this, clas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this, view, "header")
            intent.putExtra("data",bundle)
            startActivity(intent, options.toBundle())
        } else {
            intent.putExtra("data",bundle)
            startActivity(intent)
        }
    }

    fun gotoActivityUsingTransition(view: View,clas : Class<*>?) {
        val intent = Intent(this, clas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this, view, "header")
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    @SuppressLint("MissingPermission")
    fun checkInterConection(){
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkinfo = connMgr.activeNetworkInfo
        if (networkinfo != null && networkinfo.isConnected()) {
            statusInternet = true
            setLog(statusInternet.toString())
        } else {
            statusInternet = false
        }
    }

    fun showDialogFragment(fragmentDialog : DialogFragment){
        val fm = getSupportFragmentManager()
        fragmentDialog.show(fm, "yesNoAlert")
    }


    fun loadFragment(fragment: Fragment, place: Int) {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(place, fragment)
                    .addToBackStack("")
                    .commit()
        }
    }

    fun loadFragmentWithBundle(fragment: Fragment?, place: Int, bundle: Bundle) {
        if (fragment != null) {

            fragment.setArguments(bundle)

            supportFragmentManager
                    .beginTransaction()
                    .replace(place, fragment)
                    .addToBackStack("")
                    .commit()
        }
    }


    @SuppressLint("CommitTransaction")
    fun killFragment(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
        }
    }

    lateinit var callbackDialogDummy :CallbackDialogDummy

    fun showDialogDummy(callbackDialogDummy: CallbackDialogDummy){
        showDialog("")
        Handler().postDelayed({
            hideDialog()
            callbackDialogDummy.done()
        }, 1500)
    }

    interface CallbackDialogDummy{
        fun done()
    }

    fun finishResultOk(){
        val resultIntent = Intent()
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun finishResultOk(data:Intent){
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    fun finishResultCancel(context: Context){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }


}