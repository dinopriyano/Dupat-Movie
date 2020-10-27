package com.dupat.dupatmovie.ui.utils

import android.util.Patterns

fun String.validEmail(str: String) : Boolean
{
    return Patterns.EMAIL_ADDRESS.matcher(str).matches()
}