package tdtu.movieapp.app.ui.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.ui.Model.Category

class CategoryAdapter(val listCategory: List<Category>): RecyclerView.Adapter<CategoryViewHolder>(){
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
        holder.bind(category)
    }
}
class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun bind(Category:Category)
    {
        val myCategoryBtn=view.findViewById<Button>(R.id.CategoryBtn)
        myCategoryBtn.text=Category.title
    }
}