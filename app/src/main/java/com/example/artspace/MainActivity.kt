package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var indice by remember {
        mutableStateOf(0)
    }
    val listaArte= conseguirArrayList()

    Column {
        Cuadro(
            cuadro = listaArte[indice].image,
            descripcion = listaArte[indice].titulo,
            modifier = Modifier.weight(9f)
        )

        DatosArtista(
            titulo = listaArte[indice].titulo,
            artista = listaArte[indice].artista,
            anio = listaArte[indice].anio,
            modifier = Modifier.weight(2f)
        )
        Botones(
            modifier =  Modifier.weight(1f),
            accionAtras = {
                if (indice==0){
                    indice=listaArte.size-1
                }else{
                    indice -= 1
                }
            },
            accionDelante = {
                if (indice==listaArte.size-1){
                    indice=0
                }else{
                    indice += 1
                }
            }
        )
    }
}

@Composable
fun conseguirArrayList():ArrayList<Arte>{
    val listaArte = ArrayList<Arte>()
    listaArte.add(Arte(image = R.drawable.noche_estrellada, titulo = "La Noche Estrellada" , artista = "Van Gogh", anio = 1889))
    listaArte.add(Arte(image = R.drawable.meninas, titulo = "Las meninas" , artista = "Diego Velázquez", anio = 1656))
    listaArte.add(Arte(image = R.drawable.caminante, titulo = "El caminante sobre el mar de nubes" , artista = "Caspar David Friedrich", anio = 1818))
    listaArte.add(Arte(image = R.drawable.grito, titulo = "El grito" , artista = "Edvard Munch", anio = 1893))

    return listaArte
}
@Composable
fun Cuadro(
    cuadro:Int,
    descripcion :String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(22.dp)
            .shadow(5.dp, clip = false)
    ) {
        Image(
            painter = painterResource(id = cuadro),
            contentScale = ContentScale.Crop,
            contentDescription = descripcion,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        )
    }

}

@Composable
fun DatosArtista(
    titulo:String,
    artista:String,
    anio:Int,
    modifier:Modifier=Modifier
){
    Column(

        modifier = modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .background(color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.1f))
    ) {
        Text(
            text = titulo,
            fontSize = 23.sp,

            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 15.dp)
        )
        Text(
            text = "$artista ($anio)",
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 10.dp)
        )
    }
}

@Composable
fun Botones(
    modifier: Modifier=Modifier,
    accionAtras: () -> Unit,
    accionDelante: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        BotonNavegacion(
            accionAtras,
            leadingIcon = R.drawable.baseline_arrow_circle_left_24,
            descripcion = "Anterior"
        )
        BotonNavegacion(
            accionDelante,
            leadingIcon = R.drawable.baseline_arrow_circle_right_24,
            descripcion = "Siguiente"
        )
    }
}
@Composable
fun BotonNavegacion(
    onClick: () -> Unit,
    @DrawableRes leadingIcon: Int,
    descripcion:String
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(150.dp)
    ) {
        Image(painter = painterResource(id = leadingIcon), contentDescription = descripcion)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}