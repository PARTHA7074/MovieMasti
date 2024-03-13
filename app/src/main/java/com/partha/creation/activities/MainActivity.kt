package com.partha.creation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.partha.creation.R
import com.partha.creation.databinding.ActivityMainBinding
import com.partha.creation.retrofit.MyViewModel


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Observe the movie response
        viewModel.movieResponse.observe(this) { movieResponse ->
            // Update UI with the movie response
            // Example: binding.textView.text = movieResponse.someValue
            // Clear any error message if movieResponse is received successfully
            // Example: binding.errorTextView.visibility = View.GONE
            Log.d("onResponse: ", movieResponse.toString())

        }

        // Observe error message
        viewModel.errorMessage.observe(this) { errorMessage ->
            // Handle error message
            // Example: Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            // You can also update UI elements to display the error message
            // Example: binding.errorTextView.text = errorMessage
            // Example: binding.errorTextView.visibility = View.VISIBLE
            Log.e("onResponse: ", errorMessage)
            Log.d("onResponse: ", "Ended")
        }

        // Make API call
        viewModel.fetchMovies("1")

    }
}