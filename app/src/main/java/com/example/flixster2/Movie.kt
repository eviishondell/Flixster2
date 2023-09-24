package com.example.flixster2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray

@Parcelize
data class Movie(
    val movieId: Int,
    val voteAverage: Double,
    private val posterpath: String,
    val title: String,
    val overview: String,
) : Parcelable{
    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterpath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for(i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")

                    )
                )
            }
            return movies

        }
    }
}