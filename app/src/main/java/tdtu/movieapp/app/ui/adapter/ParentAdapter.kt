package tdtu.movieapp.app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.ui.viewModel.SectionModel

class ParentAdapter(val listSection:List<SectionModel>, val onClick:(Movie)->Unit):RecyclerView.Adapter<ParentAdapter.ParentMyViewHolder>() {
    inner class ParentMyViewHolder(val view:View):RecyclerView.ViewHolder(view){
        @SuppressLint("NotifyDataSetChanged")
        fun bind(Section:SectionModel, onClick: (Movie) -> Unit){
            val title=view.findViewById<TextView>(R.id.Title)
            title.text=Section.title
            val section=view.findViewById<RecyclerView>(R.id.MovieList)
            val childAdapter= Section.childlist?.let { ChildAdapter(it,onClick) }
            section.adapter=childAdapter
            section.layoutManager= LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
            childAdapter?.notifyDataSetChanged()

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentMyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val listItem=layoutInflater.inflate(R.layout.section,parent,false)
        return ParentMyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ParentMyViewHolder, position: Int) {
        val mySection=listSection[position]
        holder.bind(mySection,onClick)
    }

    override fun getItemCount(): Int {
        return listSection.size
    }
}
