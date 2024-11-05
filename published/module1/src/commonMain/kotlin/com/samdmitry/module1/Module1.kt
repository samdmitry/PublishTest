package com.samdmitry.module1

import com.samdmitry.module2.Module2

class Module1 {
    fun getMessage(module2: Module2): String {
        return getPlatformMessage() + " " + module2.getMessage()
    }
}

internal expect fun getPlatformMessage(): String