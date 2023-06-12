package com.alfayedoficial.jetpackcomposenavigationforbeginners

sealed class Screen(val route:String){
    object SplashScreen : Screen("splash_screen")
    object WizardScreen : Screen("wizard_screen")
    object LoginScreen :Screen("login_screen")
    object RegisterScreen :Screen("register_screen")
    object HomeScreen :Screen("home_screen")

    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}