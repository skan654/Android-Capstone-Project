import com.example.littlelemon.MenuItemRoom
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val imageID: Int
){
    fun toMenuItemRoom() = MenuItemRoom(
        title = title,
        price = price,
        description = description,
        category = category,
        imageID = imageID
    )
}

