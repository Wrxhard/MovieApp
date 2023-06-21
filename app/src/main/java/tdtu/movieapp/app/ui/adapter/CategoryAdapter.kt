package tdtu.movieapp.app.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Category

class CategoryAdapter(val listCategory: List<Category>,val onClick:(Category)->Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(Category: Category, onClick:(Category)->Unit)
        {
            val myCategoryBtn=view.findViewById<Button>(R.id.CategoryBtn)
            myCategoryBtn.text=Category.genre
            myCategoryBtn.setOnClickListener {
                onClick(Category)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val listCategory=layoutInflater.inflate(R.layout.category,parent,false)
        return CategoryViewHolder(listCategory)
    }


    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=listCategory[position]
        holder.bind(category,onClick)
    }
}