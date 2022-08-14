package com.example.registration_with_retrofit.extensions

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.children

fun ViewGroup.areLinesEmpty(): Boolean {
    this.children.forEach {
        if (it is AppCompatEditText && it.text?.isEmpty()!!)
            return true
    }
    return false
}