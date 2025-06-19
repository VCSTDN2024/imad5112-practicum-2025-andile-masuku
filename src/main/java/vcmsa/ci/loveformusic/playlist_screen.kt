package vcmsa.ci.loveformusic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class playlist_screen : AppCompatActivity() {

    private lateinit var displayList: TextView
    private lateinit var artist: ArrayList<String>
    private lateinit var song: ArrayList<String>
    private lateinit var rating: ArrayList<Int>
    private lateinit var comments: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist_screen)

        artist = intent.getStringArrayListExtra("artist") ?: arrayListOf()
        song = intent.getStringArrayListExtra("song") ?: arrayListOf()
        rating = intent.getIntegerArrayListExtra("rating") ?: arrayListOf()
        comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
        displayList = findViewById<TextView>(R.id.listDisplay)

        val listAllBtn: Button = findViewById(R.id.listAll)
        val rating: Button = findViewById(R.id.ratingcalc)
        val returnBtn: Button = findViewById(R.id.returnBtn)
        val exitBtn: Button = findViewById(R.id.leave)

        listAllBtn.setOnClickListener {
            displayPlayList()
        }

        rating.setOnClickListener {
            displayAvarageRating()
        }

        returnBtn.setOnClickListener {
            val intent = Intent(this, add_to_playlist::class.java)
            startActivity(intent)
        }

        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

    private fun displayPlayList() {
        val stringBuilder = StringBuilder()
        if (artist.isNotEmpty()) {
            for (i in artist.indices) {
                stringBuilder.append("Artist: ${artist[i]}\n")
                stringBuilder.append("Song: ${song[i]}\n")
                stringBuilder.append("Rating: ${rating[i]}\n")
                stringBuilder.append("Comments: ${comments[i]}\n\n")
            }
            displayList.text = stringBuilder.toString()
        } else {
            displayList.text = "Playlist is empty."
        }
    }

    private fun displayAvarageRating() {
        val stringBuilder = StringBuilder()
        var found = false
        for (i in artist.indices) {
            if (rating[i] >= 2 || rating[i] <=4) {
                stringBuilder.append("Item: ${artist[i]} (Quantity: ${rating[i]})\n")
                found = true
            }
        }
        if (found) {
            displayList.text = stringBuilder.toString()
        } else {
            displayList.text = "No items with a quantity of 2 but less than 4."
        }

    }
}