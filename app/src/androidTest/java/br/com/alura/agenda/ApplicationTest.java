package br.com.alura.agenda;

import android.app.Application;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ApplicationTestCase;
import android.view.View;
import android.widget.RatingBar;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(JUnit4.class)
public class ApplicationTest {

    @Rule
    public ActivityTestRule<FormularioActivity> activity = new ActivityTestRule<>(FormularioActivity.class);

    @Test
    public void deveriaVerificarObrigatoriedadeDosCampos() {
        Espresso.onView(ViewMatchers.withId(R.id.item_menu_confirmar)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.formulario_nome)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("Campo Obrigatório")));
        Espresso.onView(ViewMatchers.withId(R.id.formulario_telefone)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("Campo Obrigatório")));
        Espresso.onView(ViewMatchers.withId(R.id.formulario_endereco)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("Campo Obrigatório")));
    }

    @Test
    public void deveriaGravarAluno() {
        Espresso.onView(ViewMatchers.withId(R.id.formulario_nome)).perform(ViewActions.typeText("Usuario 1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.formulario_telefone)).perform(ViewActions.typeText("12345678"));
        Espresso.onView(ViewMatchers.withId(R.id.formulario_endereco)).perform(ViewActions.typeText("Rua 1"));
        Espresso.onView(ViewMatchers.withId(R.id.formulario_site)).perform(ViewActions.typeText("www.teste.com"));
        Espresso.onView(ViewMatchers.withId(R.id.formulario_nota)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.item_menu_confirmar)).perform(ViewActions.click());
    }
}