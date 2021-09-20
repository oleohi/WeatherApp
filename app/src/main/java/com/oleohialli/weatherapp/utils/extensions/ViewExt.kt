package com.oleohialli.weatherapp.utils.extensions

import android.view.View
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            listener(query.orEmpty())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            //listener(newText.orEmpty())
            return true
        }

    })
}

fun showSnackBar(view: View, message: String, actionTitle: String, action: View.OnClickListener) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setAction(actionTitle, action).show()
}