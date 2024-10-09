package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import com.example.reply.R
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation(){
       //set up compact window
        composeTestRule.setContent {
            ReplyApp(
               windowSize = WindowWidthSizeClass.Compact
            )
        }

      //Bottom navigation is displayed
        composeTestRule.onNodeWithTagForStringId(
           R.string.navigation_bottom
        ).assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail(){
       //set up medium window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }

        //Navigation Rail is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer(){
        //set up medium window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        //Permanent navigation is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
    }
}