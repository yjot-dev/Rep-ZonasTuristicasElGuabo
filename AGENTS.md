# AGENTS.md - PRINCIPIOS DE EJECUCIÓN
- **Rol del Asistente:** Se espera que el asistente actúe como un ingeniero de software senior al interpretar y ejecutar todas las reglas de este documento.
- **Prioridad del Manifiesto:** Este documento (`AGENTS.md`) es la fuente de verdad definitiva. Sus reglas tienen prioridad sobre cualquier comportamiento genérico o predeterminado del asistente.

## 1. CONTEXTO DEL PROYECTO
- **Nombre:** App Turismo El Guabo (ATEG)
- **Descripción:** Aplicación móvil diseñada para explorar los principales centros turísticos de la ciudad de El Guabo, Ecuador, utilizando Google Maps para navegación interactiva y proporcionando detalles específicos de cada destino.

## 2. STACK TECNOLÓGICO
El proyecto utiliza las siguientes tecnologías y patrones:
- **Lenguaje:** Kotlin
- **UI:** XML con Material 3
- **Arquitectura:** MVVM Simple
- **Navegación:** Navigation Component
- **Gestión de Estado:** ViewModel + StateFlow
- **Inyección de Dependencias:** Hilt
- **Persistencia/Red:** Google Maps API
- **Compatibilidad:** Android 7.0 (API 24) en adelante

## 3. ESTRUCTURA DEL PROYECTO (MVVM Simple)
Se debe seguir estrictamente la siguiente organización de directorios dentro de la capa de presentación:

- **`presentation`** (Capa de Presentación)
    - `mvvm`:
        - `ui`:
            - `fragment`: Fragments y Activities (`NombreDePantallaFragment.kt`).
            - `adapter`: Adapters para RecyclerView (`NombreDePantallaAdapter.kt`).
        - `viewmodel`: Lógica de UI (`UiViewModel.kt`).
        - `state`: Modelos de datos de UI (`UiState.kt`).
    - `navigation`: Grafos (`Navigation.kt`) y eventos (`UiEvent.kt`).

## 4. CONVENCIONES Y ESTILO
### 4.1 Código y Nomenclatura
- **Funciones, Parámetros y Variables:** camelCase.
- **Clases:** PascalCase.
- **Constantes:** UPPER_SNAKE_CASE.
- **Documentación:** Cada función pública debe incluir comentario **KDoc** describiendo su propósito.

### 4.2 Estilo del ViewModel
- Estado privado mutable: `private val _uiState = MutableStateFlow(UiState())`.
- Estado público inmutable: `val uiState: StateFlow<UiState> = _uiState.asStateFlow()`.
- Implementar siempre `onCleared()` para limpiar o resetear el estado.

## 5. RESTRICCIONES CRÍTICAS (PROHIBICIONES)
- **Dependencias:** No añadir ni actualizar dependencias en `build.gradle` o `libs.versions.toml` sin avisar previamente.
- **Seguridad:** **NUNCA** incluir ni subir al repositorio remoto los siguientes archivos:
    - `.gitignore`
    - `local.properties`
    - `custom.properties`

## 6. FLUJO DE TRABAJO Y COMUNICACIÓN
1. **Planificación:** Antes de iniciar cualquier tarea no trivial, propón un plan detallado y espera mi **"OK"**.
2. **Atomicidad:** Ejecuta una sola tarea a la vez. Al finalizar, describe exactamente qué cambios realizaste para revisión.
3. **Certeza:** Si no estás seguro de un paso o implementación al menos en un **80%**, detente y pregunta.
4. **Veracidad:** No inventes funcionalidades, rutas o comportamientos que no estén especificados.