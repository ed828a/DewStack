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
    val box = DewStack<String> (boxSize)
    var number = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showNumbers()

        fabAdd.setOnClickListener { view ->
            number ++
            with(box) {
                push(number.toString())
                showText(cursor, number.toString())
                colorBox(cursor)
            }
            showNumbers()
        }


        fabRemove.setOnClickListener { view ->
            when (box.pop()) {
                null ->
                    Snackbar.make(view,
                            "reaching the bottom of the stack", Snackbar.LENGTH_SHORT).show()
                else -> {
                    colorBox(box.cursor)
                    showNumbers()
                }
            }
        }
    }

    private fun showNumbers() {
        with(box){
            counterText.text = counter.toString()
            positionText.text = cursor.toString()
            numberText.text = number.toString()
            deepText.text = deep.toString()
            previousText.text = previousPosition.toString()
        }
    }

    private fun showText(position: Int, item: String) {
        val textView = highlightTextView(position)
        textView?.text = item
    }

    private fun colorBox(position: Int) {
        val textView = highlightTextView(box.previousPosition)
        textView?.setBackgroundColor(resources.getColor(R.color.textBackground))
        if (box.previousPosition == 0 && box.cursor == 0 && box.deep == 0) return
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
