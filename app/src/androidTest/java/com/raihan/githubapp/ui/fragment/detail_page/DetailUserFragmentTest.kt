package com.raihan.githubapp.ui.fragment.detail_page

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.raihan.githubapp.R
import com.raihan.githubapp.ui.MainActivity
import org.junit.Before
import org.junit.Test

class DetailUserFragmentTest {

	@Before
	fun setUp() {
		ActivityScenario.launch(MainActivity::class.java)
		// [[ Wait for 3 seconds ]]
		Thread.sleep(3000);
		onView(withId(R.id.rv_users))
			.perform(
				RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
					0,
					click()
				)
			)
	}

	@Test
	fun toggleFavorite() {
		onView(withId(R.id.floating_action_button))
			// [[ Double clicks to that it won't go to the favorites ]]
			.perform(click())
			.perform(click())
	}
}