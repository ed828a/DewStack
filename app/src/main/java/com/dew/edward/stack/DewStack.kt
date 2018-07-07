package com.dew.edward.stack

import android.support.design.widget.Snackbar


/**
 * Created by Edward on 7/7/2018.
 */
class DewStack<T>(val size: Int) {
    private var deep = 0
    private var counter = -1
    private var position = 0
    private var previousPosition = 0
    private val box = Array<Any>(size) { 0 }

    fun push(item: T) {
        box[position] = item as Any
        previousPosition = position
        ++counter
        position = (counter % size)
        if (deep < size - 1) deep++
    }

    fun pop(): T? {
        if (deep < 1) {
            return null
        } else {
            deep--
            previousPosition = position
            --counter
            position = (counter % size)
            return box[position] as T
        }
    }
}