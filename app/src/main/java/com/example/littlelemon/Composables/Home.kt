package com.example.littlelemon.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current
    val searchPhrase = remember {
        mutableStateOf("")
    }

        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier
                .size(60.dp)
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .weight(1f)
                    .clickable { com.example.littlelemon.Profile.route }
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column(modifier= Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = PrimaryGreen)
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment= Alignment.CenterVertically) {
            Column {
                Text(
                    text = "Little Lemon",
                    style = MaterialTheme.typography.h4,
                    color = PrimaryYellow,
                    fontWeight = FontWeight.Bold
                )
                Column(modifier = Modifier.fillMaxWidth(0.75f)) {
                    Text(text = "Chicago",
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = MaterialTheme.typography.body1,
                    color= Color.White,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(
                        id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                }
            }
            val menuItems = listOf(
                MenuItemRoom(
                    title = "Bruschetta",
                    price = "$9.99",
                    description = "This is a description of the menu item.",
                    category = "Starters",
                    imageID = R.drawable.country_roads
                ),
                MenuItemRoom(
                    title = "Pizza",
                    price = "$12.99",
                    description = "Another menu item description.",
                    category = "Mains",
                    imageID = R.drawable.road_countries
                ),
                // Add more menu items with different categories
            )
        }


    }
    TextField(
        value = searchPhrase.value,
        onValueChange = { newSearchPhrase ->
            searchPhrase.value = newSearchPhrase
        },
        placeholder = { Text("Enter Search Phrase") },
        leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }
    )


}
@Composable
fun groupMenuItemsByCategory(menuItems: List<MenuItemRoom>): Map<String, List<MenuItemRoom>> {
    return menuItems.groupBy { it.category }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(rememberNavController())
}

@Composable
fun menuItems(title: String, price: String, description: String, imageID: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.country_roads), // Pass the image URL here
            contentDescription = "Menu Item Image",
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = price,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}






