package com.raihan.githubapp.ui.fragment.search_page

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.raihan.githubapp.R
import com.raihan.githubapp.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchUserFragmentTest {

	@Before
	fun setUp() {
		ActivityScenario.launch(MainActivity::class.java)
	}

	@Test
	fun searchUsername() {
		myUsername()
	}

	@Test
	fun toDetails() {
		// [[ Wait for 3 seconds ]]
		Thread.sleep(3000);

		onView(withId(R.id.rv_users))
			.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
	}

	fun myUsername() {
		onView(withId(R.id.search_bar))
			.perform(click())
		onView(
			allOf(
				supportsInputMethods(), isDescendantOfA(
					withId(
						R.id
							.search_view
					)
				)
			)
		)
			.perform(typeText("raihanadf"))
			.perform(pressImeActionButton())
	}
}