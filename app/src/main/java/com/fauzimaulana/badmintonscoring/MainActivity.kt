package com.fauzimaulana.badmintonscoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.fauzimaulana.badmintonscoring.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var leftScore: String
    private lateinit var rightScore: String
    private var stateLeft: Int = 0
    private var stateRight: Int = 0

    companion object {
        private const val STATE_LEFT = "state_left"
        private const val STATE_RIGHT = "state_right"
        private const val STATE_COCK_LEFT = "state_cock_left"
        private const val STATE_COCK_RIGHT = "state_cock_right"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            //Do Nothing
        } else {
            binding.leftScore.text = savedInstanceState.getString(STATE_LEFT)
            binding.rightScore.text = savedInstanceState.getString(STATE_RIGHT)
            binding.imgShuttlecockLeft.visibility = savedInstanceState.getInt(STATE_COCK_LEFT)
            binding.imgShuttlecockRight.visibility = savedInstanceState.getInt(STATE_COCK_RIGHT)
        }

        binding.btnPlusLeft.setOnClickListener {
            binding.imgShuttlecockLeft.visibility = View.VISIBLE
            binding.imgShuttlecockRight.visibility = View.INVISIBLE
            val plusScore = Integer.parseInt(binding.leftScore.text.toString()) + 1
            binding.leftScore.text = plusScore.toString()
        }

        binding.btnMinLeft.setOnClickListener {
            binding.imgShuttlecockLeft.visibility = View.INVISIBLE
            val minScore = Integer.parseInt(binding.leftScore.text.toString())
            if (minScore == 0) {
                binding.leftScore.text = "0"
            } else {
                binding.leftScore.text = (minScore - 1).toString()
            }
        }

        binding.btnPlusRight.setOnClickListener {
            binding.imgShuttlecockRight.visibility = View.VISIBLE
            binding.imgShuttlecockLeft.visibility = View.INVISIBLE
            val plusScore = Integer.parseInt(binding.rightScore.text.toString()) + 1
            binding.rightScore.text = plusScore.toString()
        }

        binding.btnMinRight.setOnClickListener {
            binding.imgShuttlecockRight.visibility = View.INVISIBLE
            val minScore = Integer.parseInt(binding.rightScore.text.toString())
            if (minScore == 0) {
                binding.rightScore.text = "0"
            } else {
                binding.rightScore.text = (minScore - 1).toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reset) {
            binding.leftScore.text = "0"
            binding.rightScore.text = "0"
            binding.imgShuttlecockLeft.visibility = View.INVISIBLE
            binding.imgShuttlecockRight.visibility = View.INVISIBLE
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        leftScore = binding.leftScore.text.toString()
        rightScore = binding.rightScore.text.toString()
        stateLeft = binding.imgShuttlecockLeft.visibility
        stateRight = binding.imgShuttlecockRight.visibility
        outState.putString(STATE_LEFT, leftScore)
        outState.putString(STATE_RIGHT, rightScore)
        outState.putInt(STATE_COCK_LEFT, stateLeft)
        outState.putInt(STATE_COCK_RIGHT, stateRight)
    }
}