import com.google.gson.annotations.SerializedName

data class ComicResult(
    @SerializedName("code") val code: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("attributionText") val attributionText: String?,
    @SerializedName("attributionHTML") val attributionHTML: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("data") val data: Data?,
)


data class Data(
    @SerializedName("offset") val offset: Int?,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("total") val total: Int?,
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<ComicResponse>?,
)


data class ComicResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("digitalId") val digitalId: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("issueNumber") val issueNumber: Int?,
    @SerializedName("variantDescription") val variantDescription: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("isbn") val isbn: String?,
    @SerializedName("upc") val upc: String?,
    @SerializedName("diamondCode") val diamondCode: String?,
    @SerializedName("ean") val ean: String?,
    @SerializedName("issn") val issn: String?,
    @SerializedName("format") val format: String?,
    @SerializedName("pageCount") val pageCount: Int?,
    @SerializedName("textObjects") val textObjects: List<TextObjects>?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("urls") val urls: List<Urls>?,
    @SerializedName("series") val series: Series?,
    @SerializedName("variants") val variants: List<Any>?, // Değişken olmadığı için List<Any>? olarak ayarlanabilir.
    @SerializedName("collections") val collections: List<Any>?, // Değişken olmadığı için List<Any>? olarak ayarlanabilir.
    @SerializedName("collectedIssues") val collectedIssues: List<Any>?, // Değişken olmadığı için List<Any>? olarak ayarlanabilir.
    @SerializedName("dates") val dates: List<Dates>?,
    @SerializedName("prices") val prices: List<Prices>?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("images") val images: List<Images>?,
    @SerializedName("creators") val creators: Creators?,
    @SerializedName("characters") val characters: Characters?,
    @SerializedName("stories") val stories: Stories?,
    @SerializedName("events") val events: Events?,
)


data class TextObjects(
    @SerializedName("type") val type: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("text") val text: String?,
)


data class Urls(
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?,
)


data class Series(
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
)


data class Dates(
    @SerializedName("type") val type: String?,
    @SerializedName("date") val date: String?,
)


data class Prices(
    @SerializedName("type") val type: String?,
    @SerializedName("price") val price: Double?,
)


data class Thumbnail(
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?,
)


data class Images(
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?,
)


data class Creators(
    @SerializedName("available") val available: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<CreatorItem>?, // İsim değiştirildi
    @SerializedName("returned") val returned: Int?,
)


data class CreatorItem( // Değişiklik yapıldı
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("role") val role: String?,
)


data class Characters(
    @SerializedName("available") val available: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<CharacterItem>?, // İsim değiştirildi
    @SerializedName("returned") val returned: Int?,
)


data class CharacterItem( // Değişiklik yapıldı
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
)


data class Stories(
    @SerializedName("available") val available: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<StoryItem>?, // İsim değiştirildi
    @SerializedName("returned") val returned: Int?,
)


data class StoryItem( // Değişiklik yapıldı
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("type") val type: String?,
)


data class Events(
    @SerializedName("available") val available: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<EventItem>?, // İsim değiştirildi
    @SerializedName("returned") val returned: Int?,
)


data class EventItem( // Değişiklik yapıldı
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?,
)