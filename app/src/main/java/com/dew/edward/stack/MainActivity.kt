package com.dew.edward.stack

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val boxSize = 5
    val box = IntArray(5)
    var deep = 0
    var counter = -1
    var number = -1
    var position = 0
    var previousPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showNumbers()

        fabAdd.setOnClickListener { view ->
            previousPosition = position
            ++counter
            position = (counter % boxSize)
            number++
            box[position] = number
            showText(position)
            colorBox(position)
            if (deep < boxSize - 1) {
                deep++
            }
            showNumbers()
        }


        fabRemove.setOnClickListener { view ->

            if (deep < 1) {
                Snackbar.make(view, "reaching the bottom of the stack", Snackbar.LENGTH_SHORT).show()
            } else {
                deep--
                previousPosition = position
                --counter
                position = (counter % boxSize)
                colorBox(position)
            }
            showNumbers()
        }
    }

    private fun showNumbers() {
        counterText.text = counter.toString()
        positionText.text = position.toString()
        numberText.text = number.toString()
        deepText.text = deep.toString()

    }

    private fun showText(position: Int) {
        val textView = highlightTextView(position)
        textView?.text = box[position].toString()
    }

    private fun colorBox(position: Int) {
        val textView = highlightTextView(previousPosition)
        textView?.setBackgroundColor(resources.getColor(R.color.textBackground))
        val highlight = highlightTextView(position)
        highlight?.setBackgroundColor(resources.getColor(R.color.colorAccent))
    }

    private fun highlightTextView(position: Int) = when (position) {
        0 -> boxOne
        1 -> boxTwo
        2 -> boxThree
        3 -> boxFour
        4 -> boxFive
        else -> null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
