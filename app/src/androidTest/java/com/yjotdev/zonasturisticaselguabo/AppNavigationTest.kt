package com.yjotdev.zonasturisticaselguabo

import android.Manifest
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.GrantPermissionRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavigationTest {

    @get:Rule(order = 0)
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @Before
    fun setup() {
        // Inicializa Hilt
        hiltRule.inject()
    }

    @Test
    fun testNavegacionMarcadorCascadasDeManuel() {
        // 1. Configuramos el Intent para activar el modo test
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
            putExtra("IS_TESTING", true)
        }

        // 2. Iniciamos la actividad MainActivity
        val scenario = ActivityScenario.launch<MainActivity>(intent)

        // 3. Verificamos que el fragment de inicio está visible
        onView(withId(R.id.btnStart))
            .check(matches(isDisplayed()))

        // 4. Hacemos clic en el botón de inicio
        onView(withId(R.id.btnStart))
            .perform(click())

        // 5. Verificamos que el mapa fake y sus marcadores están visibles
        onView(withId(R.id.recyclerViewSites))
            .check(matches(isDisplayed()))

        // 6. Hacemos clic en el primer marcador fake
        onView(withId(R.id.recyclerViewSites))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // 7. Verificamos que el fragment de información está visible
        onView(withId(R.id.imgPlace))
            .check(matches(isDisplayed()))

        // 8. Cerramos el escenario
        scenario.close()
    }
}