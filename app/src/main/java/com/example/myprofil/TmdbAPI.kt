import androidx.core.location.LocationRequestCompat.Quality
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmsSemaine(@Query("api_key") apikey: String, @Query("language") language: String): TmdbResult
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key")  apikey: String, @Query("query") motcle: String) : TmdbResult

        @GET("trending/tv/week")
    suspend fun getSeriesSemaine(@Query("api_key") apikey: String, @Query("language") language: String): SerieResult
    @GET("search/tv")
    suspend fun getSeriesParMotCle(@Query("api_key")  apikey: String, @Query("query") motcle: String) : SerieResult

    @GET("trending/person/week")
    suspend fun getActeursSemaine(@Query("api_key")  apikey: String, @Query("language") language: String) :PersonResult

    @GET("search/person")
    suspend fun getActeursParMotCle(@Query("api_key") apikey: String, @Query("query")motcle: String) : PersonResult

    @GET("movie/{id}?append_to_response=credits")
    suspend fun getMovieDetails(@Path("id") id: String, @Query("api_key") apikey: String, @Query("language") language:String):MoviesDetail
    @GET("tv/{id}?append_to_response=credits")
    suspend fun getSerieDetails(@Path("id") id: String, @Query("api_key") apikey: String, @Query("language") language:String):SerieDetail
}
