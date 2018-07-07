package com.dew.edward.stack

import android.support.design.widget.Snackbar


/**
 * Created by Edward on 7/7/2018.
 */
class DewStack<T>(val size: Int) {
    private var deep = 0
    private var counter = -1
    private var cursor = 0
    private var previousPosition = 0
    private val box = Array<Any>(size) { 0 }

    fun push(item: T) {
        previousPosition = cursor
        ++counter
        cursor = (counter % size)
        box[cursor] = item as Any
        if (deep < size - 1) ++deep
    }

    fun pop(): T? {
        return if (deep < 1) {
            null
        } else {
            --deep
            previousPosition = cursor
            --counter
            cursor = (counter % size)
            box[cursor] as T
        }
    }
}