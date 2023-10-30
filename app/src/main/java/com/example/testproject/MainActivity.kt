package com.example.testproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val writeButton = findViewById<Button>(R.id.button)
        val readButton = findViewById<Button>(R.id.button3)
        val textView = findViewById<TextView>(R.id.textView)

        writeButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            // Create a new user with a first and last name
            val user = hashMapOf(
                "first" to editText.text.toString(),
                "data" to 1815,
            )

            // Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
        readButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            // Create a new user with a first and last name
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    textView.text = ""
                    for (document in result) {
                        textView.text = textView.text.toString() + "\n" + document.data.toString();
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }




    }
}