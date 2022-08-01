package com.osvin.moviestestapp.models

import com.google.gson.annotations.SerializedName

class ResponseBodyClass{

    data class ResponseBody(
        @SerializedName("copyright")
        val copyright: String,
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("num_results")
        val numResults: Int,
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("status")
        val status: String
    )

    data class Result(
        @SerializedName("byline")
        val byline: String,
        @SerializedName("critics_pick")
        val criticsPick: Int,
        @SerializedName("date_updated")
        val dateUpdated: String,
        @SerializedName("display_title")
        val displayTitle: String,
        @SerializedName("headline")
        val headline: String,
        @SerializedName("link")
        val link: Link,
        @SerializedName("mpaa_rating")
        val mpaaRating: String,
        @SerializedName("multimedia")
        val multimedia: Multimedia,
        @SerializedName("opening_date")
        val openingDate: String,
        @SerializedName("publication_date")
        val publicationDate: String,
        @SerializedName("summary_short")
        val summaryShort: String
    )

    data class Multimedia(
        @SerializedName("height")
        val height: Int,
        @SerializedName("src")
        val src: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("width")
        val width: Int
    )

    data class Link(
        @SerializedName("suggested_link_text")
        val suggestedLinkText: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    )
}