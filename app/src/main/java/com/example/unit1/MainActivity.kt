package com.example.unit1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.text.lowercase as lowercase


class MainActivity : AppCompatActivity() {
    //declaring a variable
    lateinit var wordToGuess: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //handling input via the EditText widget
        var guess1 = findViewById<TextView>(R.id.Guess1_Word)
        var guess1_check = findViewById<TextView>(R.id.Guess1_WordCheck)
        var guess2 = findViewById<TextView>(R.id.Guess2_Word)
        var guess2_check = findViewById<TextView>(R.id.Guess2_WordCheck)
        var guess3 = findViewById<TextView>(R.id.Guess3_Word)
        var guess3_check = findViewById<TextView>(R.id.Guess3_WordCheck)
        var answer_check = findViewById<TextView>(R.id.answer)


        wordToGuess = FourLetterWordList.getRandomFourLetterWord().lowercase() //gets a random word for the user

        Log.i("firetruck", wordToGuess)

        var guessButton = findViewById<Button>(R.id.purpleButton)
        var counter = 0
        guessButton.setOnClickListener{
            var guessTextView = findViewById<EditText>(R.id.userInput)
            var guessWord = guessTextView.text.toString() //asking for text in the form of a string
            if (counter == 0){
                guess1.setText(guessWord) //updating guessWord textbox
                guess1_check.setText(checkGuess(guessWord)) //outputting the check result from the guess1/guessWord
            }
            else if(counter == 1){
                guess2.setText(guessWord)
                guess2_check.setText(checkGuess(guessWord))
            }
            else if(counter == 2){
                guess3.setText(guessWord)
                guess3_check.setText(checkGuess(guessWord))
                answer_check.setText(wordToGuess) //if we reach too many guesses, tells the user the answer
                guessButton.isEnabled = false //button is grayed off
                guessButton.isClickable = false //prevents button from being clicked on
            }



            guessTextView.setText("") //overriding the text box
            counter++
        }
    }


    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result.uppercase() //prints out the Wordle answer as lowercase
    }


}

