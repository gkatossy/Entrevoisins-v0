
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAKE_ITEM = 5;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> mTempoNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbour))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Checking that Detail page is loaded and neighbour's name is also displayed
     */
    @Test
    public void showDetail() {
        Neighbour neighbour = mTempoNeighbour.get(FAKE_ITEM);
        // Select one neighbour
        onView(withId(R.id.list_neighbour)).perform(RecyclerViewActions.actionOnItemAtPosition(FAKE_ITEM,click()));
        // Show neighbour detail
        onView(withId(R.id.activity_neighbour_detail)).check(matches(isDisplayed()));
        // check textview is not empty
        onView(withId(R.id.nameProfil)).check (matches(withText(neighbour.getName())));
    }

    /**
     * When deleted an item, this item should be erased from the Main list
     */
    @Test
    public void deleteItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbour)).check(withItemCount(ITEMS_COUNT));
        // Then clicking on a delete icon
        onView(withId(R.id.list_neighbour))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Should remove one item from the list that now equals to 11
        onView(withId(R.id.list_neighbour)).check(withItemCount(ITEMS_COUNT-1));

    }
    @Test
    public void onlyFavorites() {
        // show detail selecting one item (neighbour)
        onView(withId(R.id.list_neighbour)).perform(RecyclerViewActions.actionOnItemAtPosition(FAKE_ITEM,click()));
        onView(withId(R.id.activity_neighbour_detail)).check(matches(isDisplayed()));
        // click on favorite button
        onView(withId(R.id.floatingActionButtonFav)).perform(click());
        // go back to Main list
        onView(withId(R.id.imageButtonBack)).perform(click());
        // select favorite list
        onView(withId(R.id.container)).perform(scrollRight());
        // check if the list of favorite contains at least the newly added neighbour
        onView(withId(R.id.favorite_list_neighbour)).check(matches(isDisplayed()));
        onView(withId(R.id.favorite_list_neighbour)).check(withItemCount(1));


    }
    
}