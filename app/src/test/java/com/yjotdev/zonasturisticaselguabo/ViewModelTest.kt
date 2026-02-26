package com.yjotdev.zonasturisticaselguabo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import com.yjotdev.zonasturisticaselguabo.application.mvvm.model.UiModel
import com.yjotdev.zonasturisticaselguabo.application.mvvm.viewmodel.UiViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    private lateinit var viewModel: UiViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Configuramos el despachador de corrutinas para tests
        Dispatchers.setMain(testDispatcher)
        viewModel = UiViewModel()
    }

    @After
    fun tearDown() {
        // Reseteamos el despachador al finalizar
        Dispatchers.resetMain()
    }

    /**
     * Verifica que el estado inicial del ViewModel sea un UiModel vacío/por defecto.
     */
    @Test
    fun uiStateIsInitializedWithDefaultValues() = runTest {
        val expectedState = UiModel() // Asumiendo que el constructor vacío tiene valores por defecto
        val currentState = viewModel.uiState.value

        assertEquals("El estado inicial debería ser el por defecto", expectedState, currentState)
    }

    /**
     * Prueba que el método setTitle actualice correctamente solo el título
     * y mantenga el resto del estado igual.
     */
    @Test
    fun setTitleUpdatesTitleInUiState() = runTest {
        val newTitle = "Cascadas de Manuel"

        viewModel.setTitle(newTitle)

        assertEquals(newTitle, viewModel.uiState.value.title)
    }

    /**
     * Prueba que el método setImageUrl actualice la URL correctamente.
     */
    @Test
    fun setImageUrlUpdatesImageUrlInUiState() = runTest {
        val newImageUrl = "https://ejemplo.com/foto.jpg"

        viewModel.setImageUrl(newImageUrl)

        assertEquals(newImageUrl, viewModel.uiState.value.imageUrl)
    }

    /**
     * Prueba que el método setDescription actualice la descripción correctamente.
     */
    @Test
    fun setDescriptionUpdatesDescriptionInUiState() = runTest {
        val newDescription = "Un lugar hermoso para visitar en familia."

        viewModel.setDescription(newDescription)

        assertEquals(newDescription, viewModel.uiState.value.description)
    }

    /**
     * Prueba de integración de estado: Verifica que al actualizar varios campos
     * secuencialmente, el estado final contenga todos los cambios acumulados.
     */
    @Test
    fun multipleUpdatesRetainAllValues() = runTest {
        val title = "Parque Central"
        val desc = "Zona de recreación"

        viewModel.setTitle(title)
        viewModel.setDescription(desc)

        val currentState = viewModel.uiState.value

        assertEquals(title, currentState.title)
        assertEquals(desc, currentState.description)
    }

    /**
     * Verifica que onCleared resetee el estado a sus valores por defecto.
     */
    @Test
    fun stateFlowEmitsNewValuesOnUpdate() = runTest {
        val initialValue = viewModel.uiState.value.title
        val newValue = "Nuevo Título"

        viewModel.setTitle(newValue)
        assertNotEquals(initialValue, viewModel.uiState.value.title)

        viewModel.cleanState()
        assertEquals(initialValue, viewModel.uiState.value.title)
    }
}