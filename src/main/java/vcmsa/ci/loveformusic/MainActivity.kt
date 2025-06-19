package vcmsa.ci.loveformusic

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.loveformusic.R.id
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val btn_exit = findViewById<Button>(id.exitBtn)
        val btn_start = findViewById<Button>(id.startBtn)
        val ent_name = findViewById<EditText>(id.enterName)
        btn_start.setOnClickListener {


            if (ent_name.text.toString().isEmpty()) {
                Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, add_to_playlist::class.java)
                startActivity(intent)
                finish()
            }
        }
        btn_exit.setOnClickListener {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }

    }
}