package ru.skillbranch.devintensive

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {
    lateinit var  benderImage:ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        benderImage = findViewById(R.id.iv_bender)
        textTxt = findViewById(R.id.tv_text)
        messageEt = findViewById(R.id.et_message)
        sendBtn = findViewById(R.id.iv_send)

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTIONS") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status),Bender.Question.valueOf(question))

        val d = Log.d("M_MainActivity", "onCreate $status $question")
        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
        messageEt.setOnEditorActionListener(this)
        messageEt.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE)

    }
    override fun onRestart(){
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")

    }
    override fun onStart(){
        super.onStart()
        Log.d("M_MainActivity", "onStart")

    }
    override fun onResume(){
        super.onResume()
        Log.d("M_MainActivity", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTIONS", benderObj.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${benderObj.status.name}  ${benderObj.question.name}")

    }
    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send){
            val (phrase, color)  =  benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
    }
    

    private fun Activity.hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            sendMessage()
            return true
        }
        return false
    }

    private fun sendMessage() {
        val (phrase, color)  =  benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
        hideKeyboard()
    }


}



