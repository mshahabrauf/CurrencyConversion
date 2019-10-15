package com.golootlo.webview.currencyexchange.extras


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.golootlo.webview.currencyexchange.R


class ProgressLoader : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(activity!!)

        // request a window without the title
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val inflater = activity!!.layoutInflater
        val parent = inflater.inflate(R.layout.fragment_progress_loader, null)
        dialog.setContentView(parent)
        isCancelable = false
        dialog.setCanceledOnTouchOutside(false)
        //Set the dialog to immersive
        dialog.window?.decorView?.systemUiVisibility = activity?.window?.decorView?.systemUiVisibility!!
        return dialog

    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if (this.isAdded) {
                return  //or return false/true, based on where you are calling from
            }
            val ft = manager.beginTransaction()
            ft.add(this, TAG)

            /*try catch is used to tackle the crash
             when user close the app and some thing
             running in background of app*/

            ft.commitAllowingStateLoss()


        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

    }

    override fun dismiss() {
        try {
            super.dismiss()
        } catch (e: IllegalStateException) {
            super.dismissAllowingStateLoss()
        }

    }

    companion object {

        private val TAG = "ProgressLoader"
        private var loader: ProgressLoader? = null

        val progressLoader: ProgressLoader?
            get() {
                if (loader == null) {
                    loader = ProgressLoader()

                }
                return loader

            }

        fun hideProgress() {
             loader?.dismissAllowingStateLoss()
        }
    }
}
