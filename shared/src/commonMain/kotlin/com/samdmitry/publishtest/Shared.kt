package com.samdmitry.publishtest

import com.samdmitry.module1.Module1
import com.samdmitry.module2.Module2

class Shared {
    fun getMessage() = Module1().getMessage(Module2())
}
