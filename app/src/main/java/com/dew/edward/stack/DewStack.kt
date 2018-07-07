package com.dew.edward.stack

import android.support.design.widget.Snackbar


/**
 * Created by Edward on 7/7/2018.
 */
class DewStack<T>(val size: Int) {
     var deep = 0
     var counter = -1
     var cursor = 0
     var previousPosition = 0
    private val box = Array<Any>(size) { 0 }

    fun push(item: T) {
        previousPosition = cursor
        ++counter
        cursor = (counter % size)
        box[cursor] = item as Any
        if (deep < size - 1 ) ++deep
    }

    fun pop(): T? {
         if (deep < 1) {
            return null
        } else {
            --deep
            previousPosition = cursor
            --counter
            cursor = (counter % size)
             if (cursor == -1) cursor = 0
             return box[cursor] as T
        }
    }
}