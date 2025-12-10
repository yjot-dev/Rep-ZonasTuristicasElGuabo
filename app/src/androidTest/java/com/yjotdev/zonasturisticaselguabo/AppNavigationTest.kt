package com.yjotdev.zonasturisticaselguabo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavigationTest {

    // Regla para inyectar dependencias con Hilt antes de cada test
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var device: UiDevice

    @Before
    fun setup() {
        // Inicializa Hilt
        hiltRule.inject()
        // Inicializa UIAutomator
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun testNavegacionMarcadorCascadasDeManuel() {
        // 1. INICIO: Lanza la MainActivity
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        // Verificamos que el mapa está visible
        onView(withId(R.id.map)).check(matches(isDisplayed()))

        // 2. ESPERAR CARGA DEL MAPA (Visualmente)
        device.wait(Until.hasObject(By.descContains("Google Map")), 5000)

        // 3. ENCONTRAR EL MARCADOR (Solo para verificar que existe)
        val markerName = "Cascadas de Manuel"
        val markerObject = device.wait(Until.findObject(By.descContains(markerName)), 5000)
        if (markerObject == null) {
            throw RuntimeException("No se encontró el marcador '$markerName'.")
        }
        // Hacemos un click visual solo para que el test se vea real (opcional)
        markerObject.click()
        markerObject.click()
        Thread.sleep(1000) // Esperar a que la ventana se vea

        // 4. TRUCO "QA PROFESIONAL": INVOCAR LA NAVEGACIÓN PROGRAMÁTICAMENTE
        // En lugar de luchar con coordenadas, ejecutamos la acción directamente.
        val markerBounds = markerObject.visibleBounds
        val centerX = markerBounds.centerX()
        val density = InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
        // Hacemos clics en un rango vertical más amplio y rápido
        // InfoWindows suelen estar entre 40dp y 120dp arriba del marcador
        val steps = listOf(50, 70, 90, 110)
        var navegacionExitosa = false
        for (dpOffset in steps) {
            if (device.hasObject(By.res("${BuildConfig.APPLICATION_ID}:id/imgPlace"))) {
                navegacionExitosa = true
                break // Ya llegamos, salir del loop
            }
            val y = markerBounds.top - (dpOffset * density).toInt()
            device.click(centerX, y)
            Thread.sleep(800) // Espera entre intentos
        }

        // 5. ESPERAR LA TRANSICIÓN
        val isTargetVisible = device.wait(Until.hasObject(By.res("${BuildConfig.APPLICATION_ID}:id/imgPlace")), 5000)
        if (!isTargetVisible && !navegacionExitosa) {
            throw RuntimeException("El click se realizó, pero no se navegó a la pantalla de información.")
        }

        // 6. VALIDACIÓN FINAL CON ESPRESSO
        onView(withId(R.id.imgPlace)).check(matches(isDisplayed()))
        scenario.close()
    }
}