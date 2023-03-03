package tdtu.movieapp.app.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.ui.ViewModel.ChildModel

class ChildAdapter(val listFilm: List<ChildModel>,val onClick:(ChildModel)->Unit): RecyclerView.Adapter<ChildMyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildMyViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val listFilm=layoutInflater.inflate(R.layout.card,parent,false)
        return ChildMyViewHolder(listFilm)
    }

    override fun onBindViewHolder(holder: ChildMyViewHolder, position: Int) {
        val film=listFilm[position]
        holder.bind(film,onClick)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}
class ChildMyViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun bind(Film: ChildModel,onClick: (ChildModel) -> Unit)
    {
        val myFilmPicture=view.findViewById<ImageView>(R.id.imageView)
        val myFilmDesc=view.findViewById<TextView>(R.id.moviedesc)
        myFilmPicture.setImageResource(Film.image)
        myFilmDesc.text=Film.desc
        view.setOnClickListener{
            onClick(Film)
        }
    }
}