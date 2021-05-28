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
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var pDialog: ProgressDialog
    protected var statusInternet: Boolean = false
    protected val binding: VB by lazy { bindLayout() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)

        onMain()
    }

    abstract fun bindLayout(): VB


    abstract fun onMain()

    protected fun setLog(message: String) {
        if(BuildConfig.DEBUG){
            Log.e("Test", message)
        }
    }

    protected fun setLog(tag:String,message: String) {
        if(BuildConfig.DEBUG){
            Log.e(tag, message)
        }
    }

    fun setToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
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

    fun closeApplication(message: String) {
        android.app.AlertDialog.Builder(this)
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

    @SuppressLint("MissingPermission")
    fun checkInterConnection() {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        statusInternet = networkInfo != null && networkInfo.isConnected
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

    protected fun showDialog(Mdialog: String) {
        if (!pDialog.isShowing)
            pDialog.setMessage(Mdialog)
        pDialog.show()
    }

    protected fun hideDialog() {
        if (pDialog.isShowing)
            pDialog.dismiss()
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



}