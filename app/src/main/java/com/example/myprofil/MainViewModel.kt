import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val acteurs = MutableStateFlow<List<Person>>(listOf())
    val moviesDetails = MutableStateFlow(MoviesDetail())
    val seriesDetails = MutableStateFlow(SerieDetail())
    val apikey = "82fb6ce9f6ac5a39cc14cab90876dffd"
    val language = "fr"
    val filmId =MutableStateFlow("")
    val serieId = MutableStateFlow("")
    var rememberSelectedItem = MutableStateFlow("")
    var rememberSelectedItemPortrait = MutableStateFlow("")
    var iconeVisible = MutableStateFlow(true)






    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)


    fun getWeekMovies() {
        viewModelScope.launch {
            movies.value = service.getFilmsSemaine(apikey, language).results
        }
    }
    fun getWeekSeries() {

        viewModelScope.launch {
            series.value = service.getSeriesSemaine(apikey, language).results

        }


    }
    fun getWeekActeurs() {
        viewModelScope.launch {
            acteurs.value = service.getActeursSemaine(apikey, language).results

        }

    }
    fun searchMovies(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmsParMotCle(apikey, motcle).results
        }
    }
    fun searchSerie(motcle: String){
        viewModelScope.launch {
            series.value = service.getSeriesParMotCle(apikey, motcle).results
        }
    }
    fun searchActeur(motcle: String){
        viewModelScope.launch {
            acteurs.value = service.getActeursParMotCle(apikey, motcle).results
        }
    }
    fun getFilmDetails(id: String){

        viewModelScope.launch {
            moviesDetails.value = service.getMovieDetails(id, apikey, language)
        }
    }
    fun getSerieDetails (id: String){
        viewModelScope.launch {
            seriesDetails.value = service.getSerieDetails(id, apikey, language)
        }
    }

}
