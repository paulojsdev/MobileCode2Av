package devandroid.listavip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devandroid.listavip.R

class AdapterListaVip(private val userList: ArrayList<dbListaVip>) :
    RecyclerView.Adapter<AdapterListaVip.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nomeUsuario : TextView = itemView.findViewById(R.id.nomeEditText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.nomeUsuario.text =currentUser.nome
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}