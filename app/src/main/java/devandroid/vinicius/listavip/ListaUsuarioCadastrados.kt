package devandroid.vinicius.listavip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListaUsuarioCadastrados : AppCompatActivity() {

    private lateinit var usuariosRecyclerView: RecyclerView
    private lateinit var usuarioAdapter: AdapterListaVip
    private lateinit var dbRef: DatabaseReference
    private lateinit var usuariosListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuario_cadastrados)

        usuariosRecyclerView = findViewById(R.id.usuariosRecyclerView)
        usuariosRecyclerView.layoutManager = LinearLayoutManager(this)
        usuarioAdapter = AdapterListaVip(emptyList())
        usuariosRecyclerView.adapter = usuarioAdapter

        dbRef = FirebaseDatabase.getInstance().reference.child("Usuário")

        usuariosListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val usuarios = mutableListOf<dbListaVip>()
                for (dataSnapshot in snapshot.children) {
                    val usuario = dataSnapshot.getValue(dbListaVip::class.java)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
    }
}