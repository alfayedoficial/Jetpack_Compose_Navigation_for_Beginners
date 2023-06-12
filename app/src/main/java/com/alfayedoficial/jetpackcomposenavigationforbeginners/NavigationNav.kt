package com.alfayedoficial.jetpackcomposenavigationforbeginners

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alfayedoficial.jetpackcomposenavigationforbeginners.ui.theme.orangish
import com.alfayedoficial.jetpackcomposenavigationforbeginners.ui.theme.purplish
import kotlinx.coroutines.delay
import java.util.Stack

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route, builder = {
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.WizardScreen.route){
            WizardScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.HomeScreen.route + "/{username}"
            , arguments = listOf(
                navArgument("username"){
                    type = NavType.StringType
                    defaultValue = "Test"
                    nullable = true
            })
        ){entry ->
            HomeScreen(entry.arguments?.getString("username"))
        }
    })
}



@Composable
fun SplashScreen(navController: NavController) {

    var startAnimation by remember{ mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f
        , animationSpec = tween(durationMillis = 3000 ), label = ""
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.navigate(Screen.WizardScreen.route)
    }

    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .alpha(alphaAnim.value)
        , contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector =  Icons.Default.Email
            , contentDescription = null
            , tint = Color.Black)
    }

}

@Preview
@Composable
fun PreviewSplashScreen() {
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        , contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector =  Icons.Default.Email
            , contentDescription = null
            , tint = Color.Black)
    }

}


@Composable
fun WizardScreen(navController: NavController) {
    Box  {
        BgCard2(navController)
        MainCard2()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWizardScreen() {
    Box  {
        BgCard2(null)
        MainCard2()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)) {


        OutlinedTextField(value = username, onValueChange = { username = it }
            ,label = { Text(text = "username")}
            , placeholder = { Text(text = "Enter your username")}
            , leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Lock Icon"
                )
            })

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }
            , label ={ Text(text = "password") }
            , placeholder = { Text(text = "Enter your password")}
            , leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Lock Icon"
                )
            }
            ,trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = if (showPassword) "Show Password" else "Hide Password"
                    )
                }
            }
            ,visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (username == "ali" && password == "123456"){
                    Toast.makeText(context, "Login is successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.HomeScreen.withArgs(username))
                }else{
                    Toast.makeText(context, "Your password is wrong, try again", Toast.LENGTH_LONG).show()
                }
            } ,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(20.dp, top = 0.dp, end = 20.dp, bottom = 0.dp)) {
            Text(text = "Login")
        }


    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewLoginScreen() {
    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)) {


        OutlinedTextField(value = username, onValueChange = { username = it }
            ,label = { Text(text = "username")}
            , placeholder = { Text(text = "Enter your username")}
            , leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Lock Icon"
                )
            })

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }
            , label ={ Text(text = "password") }
            , placeholder = { Text(text = "Enter your password")}
            , leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Lock Icon"
                )
            }
            ,trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = if (showPassword) "Show Password" else "Hide Password"
                    )
                }
            }
            ,visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (username == "ali" && password == "123456"){
                    Toast.makeText(context, "Login is successfully", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "Your password is wrong, try again", Toast.LENGTH_LONG).show()
                }
            } ,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(20.dp, top = 0.dp, end = 20.dp, bottom = 0.dp)) {
            Text(text = "Login")
        }


    }
}


@Composable
fun HomeScreen(name :String?) {

    Box(contentAlignment = Alignment.Center ,
        modifier = Modifier.fillMaxSize()) {
        Text(text ="Hello, $name")
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {

    val name = "ali"

    Box(contentAlignment = Alignment.Center , 
        modifier = Modifier.fillMaxSize()) {
        Text(text ="Hello, $name")
    }
}


@Composable
fun BgCard2(navController: NavController?) {
    val signupText = buildAnnotatedString {
        append("Don't have an account? ")
        withStyle(SpanStyle(color = orangish)) {
            append("Sign up here!")
        }
    }
    Surface(color = purplish, modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(y = (-30).dp)
        ) {
            Row {
                Box(modifier = Modifier
                    .background(color = Color.White, shape = CircleShape)
                    .size(10.dp))
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Box(modifier = Modifier
                    .background(color = Color.Gray, shape = CircleShape)
                    .size(12.dp))
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Box(modifier = Modifier
                    .background(color = Color.Gray, shape = CircleShape)
                    .size(10.dp))
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                TextButton(onClick = {
                    navController?.navigate(Screen.LoginScreen.route)
                }) {
                    Text(text = "Skip", color = Color.White)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {

                    }) {
                        Text(text = "Next", color = Color.White)
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Image(painter  = painterResource(R.drawable.ic_next), contentDescription =null )
                    }
                }
            }
        }
    }
}


@Composable
fun MainCard2() {
    Surface(color = Color.White, modifier = Modifier
        .height(600.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.ic_cleaning), contentDescription = null )
            Spacer(modifier = Modifier.padding(32.dp))

            CompositionLocalProvider() {
                Text(text = "Cleaning on Demand",style = MaterialTheme.typography.headlineLarge)
            }

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            CompositionLocalProvider() {
                Text(text = "Book an appointment in less than 60 seconds and get on the schedule as early as tomorrow.",
                    style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
            }


        }
    }
}