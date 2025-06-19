package vcmsa.ci.loveformusic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class add_to_playlist : AppCompatActivity() {

    private val artist = mutableListOf<String>()
    private val song = mutableListOf<String>()
    private val rating = mutableListOf<Int>()
    private val comment = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_to_playlist)

        val addBtn = findViewById<Button>(R.id.add_Btn)
        val viewBtn = findViewById<Button>(R.id.view_Btn)
        val exitBtn = findViewById<Button>(R.id.exit_Btn)

        addBtn.setOnClickListener {
            showAddPlayList()
        }

        viewBtn.setOnClickListener {
            if (artist.isNotEmpty()) {
                val intent = Intent(this, playlist_screen::class.java)
                intent.putStringArrayListExtra("Artist Name", ArrayList(artist))
                intent.putStringArrayListExtra("Song Title", ArrayList(song))
                intent.putIntegerArrayListExtra("Rating", ArrayList(rating))
                intent.putStringArrayListExtra("Comments", ArrayList(comment))
                startActivity(intent)
            } else {
                Snackbar.make(viewBtn, "Playlist is Blank... Please add content.", Snackbar.LENGTH_SHORT).show()
            }

            exitBtn.setOnClickListener {
                finishAffinity()
                exitProcess(0)
            }
            
        }
    }

    private fun showAddPlayList() {
        val constructor =AlertDialog.Builder(this)
        constructor.setTitle("Add New Playlist")

        val show = layoutInflater.inflate(R.layout.adding_page,null)
        val artistName = findViewById<EditText>(R.id.artistName)
        val songTitle = findViewById<EditText>(R.id.songTitle)
        val rates = findViewById<EditText>(R.id.rate)
        val comments = findViewById<EditText>(R.id.commentInit)

        constructor.setView(show)

        constructor.setPositiveButton("Add"){ dialog, _ ->
            val artistname = artistName.text.toString().trim()
            val songtitle = songTitle.text.toString().trim()
            val ratings = rates.text.toString().trim()
            val comment = comments.text.toString().trim()

            if (artistname.isEmpty() || songtitle.isEmpty() || rating.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), " Artist Name, Song Title, and Rating cannot be Blank.", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val rate = ratings.toIntOrNull()
            if (rate == null || rate <= 0) {
                Snackbar.make(findViewById(android.R.id.content), "Invalid Rating. Please Rate from 1 going Up, Snackbar.LENGTH_SHORT).show()",
                    return@setPositiveButton)
            }

            artist.add(artistname)
            this.song.add(songtitle)
            this.rating.add(ratings)
            this.comment.add(comment)

            Snackbar.make(findViewById(android.R.id.content), "$artistname added to Playlist.", Snackbar.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        constructor.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        constructor.show()
    }



}

private fun <E> MutableList<E>.add(element: String) {

}

