package com.example.rappiinterview.ui.util

import android.app.Activity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.rappiinterview.R

class PopUpsUtils {
    fun showAlertDialog(activity: Activity, description: String) {
        val alertDialog = AlertDialog.Builder(activity).create()
        val view = activity.layoutInflater.inflate(R.layout.popup_general_error, null)
        alertDialog.setView(view)
        view.findViewById<TextView>(R.id.description).text = description
        view.findViewById<TextView>(R.id.title).text = activity.getString(R.string.app_name)
        val okButton = view.findViewById<Button>(R.id.buttonOK)
        okButton.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}