package tdtu.movieapp.app.ui.ViewModel

import tdtu.movieapp.app.data.model.Treding.TredingMovie
import tdtu.movieapp.app.ui.Model.ListFilmModel

class SectionModel(tille:String, var childlist: List<ListFilmModel>?) {
    var title=tille
}
