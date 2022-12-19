package com.example.coursework.helpers

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(field: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(field: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(field: Editable?) {
    }
}