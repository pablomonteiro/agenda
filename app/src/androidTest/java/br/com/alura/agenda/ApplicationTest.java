package br.com.alura.agenda;

import android.app.Application;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ApplicationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(JUnit4.class)
public class ApplicationTest {

    @Rule
    public ActivityTestRule<FormularioActivity> activity = new ActivityTestRule<>(FormularioActivity.class);

    @Test
    public void firstTest() {
        Espresso.onView(ViewMatchers.withId(R.id.formulario_nome)).check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

}