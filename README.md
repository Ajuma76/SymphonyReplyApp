Reply App - Starter Code
=================================
Starter code for the Android Basics with Compose: Reply app.

4. Change screens without a navigation graph
   In the previous pathway, you learned to use a NavHostController class to navigate from one screen to another. 
5. With Compose, you can also change screens with simple conditional statements by making use of runtime mutable states. 
This is especially useful in small applications like the Reply app, where you only want to switch between two screens.
6. In Compose, screens are recomposed when a state change occurs. 
You can change screens using simple conditionals to respond to changes in states.
7. You'll use conditionals to show the content of the home screen when the user is at the home screen,
and the details screen when the user is not at the home screen.

The WindowSizeClass API introduced for Compose makes the implementation of Material Design breakpoints simpler.

Window Size Classes introduces three categories of sizes: Compact, Medium, and Expanded, for both width and height.