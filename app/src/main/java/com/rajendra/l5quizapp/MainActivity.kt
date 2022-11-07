package com.rajendra.l5quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.rajendra.l5quizapp.entity.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var radioTrue: RadioButton
    private lateinit var radioFalse: RadioButton
    private lateinit var radioGroup: RadioGroup


    private lateinit var checkBoxI: CheckBox
    private lateinit var checkBoxII: CheckBox
    private lateinit var checkBoxIIII: CheckBox
    private lateinit var checkBoxIV: CheckBox
    private lateinit var checkBoxV: CheckBox

    private lateinit var reset: Button
    private lateinit var submit: Button

    private var result1 = ""
    private var result2 = ""

    private val quizCollection = ArrayList<Question>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing Views
        init()
    }

    private fun init() {
        radioTrue = findViewById(R.id.radioQ1True)
        radioFalse = findViewById(R.id.radioQ1False)
        radioGroup = findViewById(R.id.radioGroup)

        checkBoxI = findViewById(R.id.checkboxQ2Opt1)
        checkBoxII = findViewById(R.id.checkboxQ2Opt2)
        checkBoxIIII = findViewById(R.id.checkboxQ2Opt3)
        checkBoxIV = findViewById(R.id.checkboxQ2Opt4)
        checkBoxV = findViewById(R.id.checkboxQ2Opt5)

        reset = findViewById(R.id.btnReset)
        submit = findViewById(R.id.btnSubmit)

        recordQuizData()

        reset.setOnClickListener(View.OnClickListener {
            resetForm()
        })

        submit.setOnClickListener(View.OnClickListener {
            submitQuiz()
        })

    }

    private fun submitQuiz() {
        var score = 0

        val current = LocalDateTime.now()

        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(formatterDate)

        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = current.format(formatterTime)

        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioQ1True ->
                    result1 = "A"

                R.id.radioQ1False ->
                    result1 = "B"
            }
        }

        readAnswer2()

        if (validateAnswer1()) {
            score += 50
        }
        if (validateAnswer2()) {
            score += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $date and Time: $time, You achieved $score %"

        displayDialog(messageToDisplay, "Tadha..")
    }

    private fun displayDialog(msg: String, title: String) {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }

        builder.setMessage(msg)!!.setTitle(title)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun validateAnswer1(): Boolean {
        val quiz = quizCollection[0]
        if (quiz.ans == result1) {
            return true
        }
        return false
    }

    private fun validateAnswer2(): Boolean {
        val quiz = quizCollection[1]
        if (quiz.ans == result2) {
            return true
        }
        return false
    }

    private fun readAnswer2() {
        result2 = ""
        if (checkBoxI.isChecked) {
            result2 += "A"
        }
        if (checkBoxII.isChecked) {
            result2 += "B"
        }
        if (checkBoxIIII.isChecked) {
            result2 += "C"
        }
        if (checkBoxIV.isChecked) {
            result2 += "D"
        }
        if (checkBoxV.isChecked) {
            result2 += "E"
        }

    }

    private fun resetForm() {
        //Reset question I
        radioFalse.isChecked = false
        radioTrue.isChecked = false

        //Reset Question II
        checkBoxI.isChecked = false
        checkBoxII.isChecked = false
        checkBoxIIII.isChecked = false
        checkBoxIV.isChecked = false
        checkBoxV.isChecked = false


        //reset answer variable
        result1 = ""
        result2 = ""
    }

    private fun recordQuizData() {
        val question1 = Question(
            "A", 1
        )

        val question2 = Question(
            "ADE", 2
        )
        quizCollection.add(question1)
        quizCollection.add(question2)
    }
}