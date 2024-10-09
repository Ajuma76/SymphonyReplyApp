package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import com.example.reply.R
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get: Rule

    //To access empty activity, the code uses ComponentActivity instead of MainActivity.
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailRetained_afterConfigChange(){
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        //Given third email is displayed.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        //Open details page
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        //verify that it shows the detailed screen of the correct email
        //assert that the back button exists.
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()

        //simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        //verify that it still shows the detailed screen of the same email.
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailRetained_afterConfigChange(){
        //setup expanded window

        val stateRestorationTester = StateRestorationTester(composeTestRule)

        stateRestorationTester.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        //verify that the third email is displayed.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        //select the third email
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        //verify that the third email is displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
            )))
        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that third email is still displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
            )
    }

}