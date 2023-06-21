package tdtu.movieapp.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Movie

class SearchAdapter(val searchItems:List<Movie>,val onClick:(Movie)->Unit):RecyclerView.Adapter<SearchAdapter.MovieHolder>() {
    inner class MovieHolder(val view:View):RecyclerView.ViewHolder(view)
    {
        fun bind(Item: Movie, onClick: (Movie) -> Unit)
        {
            val picUrl ="https://image.tmdb.org/t/p/original${Item.poster_path}"
            val myFilmPicture=view.findViewById<ImageView>(R.id.SearchimageView)
            val myFilmName=view.findViewById<TextView>(R.id.movie_name)
            Glide.with(view)
                .load(picUrl)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(myFilmPicture)
            myFilmName.text=Item.title
            view.setOnClickListener{
                onClick(Item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val searchitems=layoutInflater.inflate(R.layout.search_item,parent,false)
        return MovieHolder(searchitems)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item=searchItems[position]
        holder.bind(item,onClick)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }
}