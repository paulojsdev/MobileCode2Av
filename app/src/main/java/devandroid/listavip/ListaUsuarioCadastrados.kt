package devandroid.listavip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListaUsuarioCadastrados : AppCompatActivity() {

    private lateinit var usuariosRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var dbRef: DatabaseReference
    private lateinit var userList: ArrayList<dbListaVip>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuario_cadastrados)

        usuariosRecyclerView = findViewById(R.id.usuariosRecyclerView)
        usuariosRecyclerView.layoutManager = LinearLayoutManager(this)
        usuariosRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        userList = arrayListOf<dbListaVip>()

        getUsuariosData()

    }
    private fun getUsuariosData() {

        usuariosRecyclerView.visibility =View.GONE
        tvLoadingData.visibility =View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().reference.child("Usuário")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                if (snapshot.exists()) {
                    for (userSnap in snapshot.children) {
                        val userData = userSnap.getValue(dbListaVip::class.java)
                        userList.add(userData!!)
                    }
                    val userAdapter = AdapterListaVip(userList)
                    usuariosRecyclerView.adapter = userAdapter

                    usuariosRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}