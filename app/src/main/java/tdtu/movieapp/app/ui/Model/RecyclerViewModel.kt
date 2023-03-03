package tdtu.movieapp.app.ui.ViewModel

class ParentModel(tille:String,childlist: List<ChildModel>) {
    var title=tille
    var childlist=childlist
}
data class ChildModel(val image:Int,val desc:String)
data class Category(val title:String)