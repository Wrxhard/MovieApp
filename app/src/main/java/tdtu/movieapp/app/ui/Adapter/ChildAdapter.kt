package tdtu.movieapp.app.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Treding.TredingMovie

class ChildAdapter(val listFilm: List<TredingMovie>, val onClick:(TredingMovie)->Unit): RecyclerView.Adapter<ChildMyViewHolder>(){
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
    fun bind(Film: TredingMovie, onClick: (TredingMovie) -> Unit)
    {
        val picUrl ="https://image.tmdb.org/t/p/original${Film.poster_path}"
        val myFilmPicture=view.findViewById<ImageView>(R.id.imageView)
        val myFilmDesc=view.findViewById<TextView>(R.id.moviedesc)
        Glide.with(view)
            .load(picUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(myFilmPicture)
        myFilmDesc.text=Film.title
        view.setOnClickListener{
            onClick(Film)
        }
    }
}