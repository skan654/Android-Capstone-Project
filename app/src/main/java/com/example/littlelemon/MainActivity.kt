import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.Navigating
import com.example.littlelemon.R
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val NavController = rememberNavController()
            Navigating(context = applicationContext, navController = rememberNavController())
        }


    }
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation)
        Json { var contentType = ContentType("text", "plain") }
    }
    private val database: AppDatabase
    init {
        database = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    fun getAllDatabaseMenuItems(): LiveData<List<MenuItemRoom>> {
        return database.menuItemDao().getAll()
    }

    private suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
        val res: HttpResponse = httpClient.get(url)
        val menuData: MenuNetworkData = res.body()
        val menuItems: List<MenuItemNetwork> = menuData.menu
        return menuItems
    }

    fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }









}

