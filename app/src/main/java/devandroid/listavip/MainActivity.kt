package devandroid.listavip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbRef = FirebaseDatabase.getInstance().getReference("Usuário")


        val nomeEditText = findViewById<EditText>(R.id.nomeEditText)
        val telefoneEditText = findViewById<EditText>(R.id.telefoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val cadastrarButton = findViewById<Button>(R.id.cadastrarButton)
        val proximaPaginaButton = findViewById<Button>(R.id.proximaPaginaButton)


        cadastrarButton.setOnClickListener {
            val nome = nomeEditText.text.toString()
            val telefone = telefoneEditText.text.toString()
            val email = emailEditText.text.toString()


            if (nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty()) {

                val key = dbRef.push().key!!


                val usuario = dbListaVip(nome, email, telefone)


                dbRef.child(key).setValue(usuario).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }

        proximaPaginaButton.setOnClickListener {

            val intent = Intent(this, ListaUsuarioCadastrados::class.java)
            startActivity(intent)
        }
    }
}