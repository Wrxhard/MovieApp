package tdtu.movieapp.app.data.local.service

import androidx.room.TypeConverter
import tdtu.movieapp.app.data.model.Movies.Category

class Converter {
    @TypeConverter
    fun categories(list:List<Category>):String{
        var res=""
        list.forEach {
            res+=it.genre+","
        }
        if (res.endsWith(","))
        {
            res.dropLast(1)
        }
        return res
    }
    @TypeConverter
    fun toCategory(categories: String):List<Category>{
        val list= mutableListOf<Category>()
        categories.split(",").forEach {
                list.add(Category(it))
        }
        return list
    }
}