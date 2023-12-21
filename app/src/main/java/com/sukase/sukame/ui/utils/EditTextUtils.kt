package com.sukase.sukame.ui.utils


import com.sukase.sukame.ui.base.CannotBlankEditText

object EditTextUtils {
    fun isNotBlankEdtField(vararg edt: CannotBlankEditText): Boolean {
        for ((index, value) in edt.withIndex()) {
            if (value.error.isNullOrBlank()) {
                continue
            } else if (index == edt.lastIndex) {
                return true
            } else {
                break
            }
        }
        return false
    }
}