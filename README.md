# Zonas Turísticas El Guabo (ZTEG)
ZTEG es una aplicación móvil diseñada para explorar los principales centros turísticos de la ciudad de El Guabo, Ecuador. El objetivo principal es ofrecer a los usuarios una herramienta intuitiva y directa para descubrir y obtener información sobre los puntos de interés locales, utilizando la API de Google Maps como base para una experiencia de navegación interactiva.

# Características principales
- 🪟 Interfaz clasica con XML
- 📊 Integración con ViewModel + StateFlow
- 🎨 Patrón de diseño arquitectónico con MVVM
- 💉 Inyección de dependencias con Hilt
- 📱 Compatible con Android 7.0 (API 24) en adelante

# Instalación
- Clona el repositorio: git clone https://github.com/yjot-dev/Rep-ZonasTuristicasElGuabo.git
- Abre el proyecto en Android Studio (Giraffe o superior)
- Sincroniza dependencias con Gradle
- Conecta un dispositivo o emulador y ejecuta la app

# Tecnologías usadas
- Kotlin
- XML
- AndroidX (Lifecycle, Core KTX)
- Material 3

# Uso
El flujo de uso de la aplicación está diseñado para ser sencillo y eficiente, guiando al usuario a través de los siguientes pasos:

1. Exploración en el Mapa Interactivo: Al abrir la aplicación, el usuario es recibido con una vista de mapa centrada en la ciudad de El Guabo. Sobre el mapa se despliegan múltiples marcadores que representan los diferentes destinos turísticos disponibles en la zona. Esta pantalla inicial permite al usuario tener una visión geográfica completa de los lugares de interés a su alrededor.
2. Identificación y Selección del Destino: El usuario puede interactuar libremente con el mapa. Al hacer un solo clic sobre cualquiera de los marcadores, se muestra el nombre del lugar turístico, permitiendo una rápida identificación sin salir de la vista principal.
3. Acceso a la Información Detallada: Si un lugar capta el interés del usuario, un segundo clic sobre el mismo marcador lo redirige a una nueva pantalla. Esta vista de "Información" está dedicada exclusivamente a proporcionar detalles sobre el sitio seleccionado, como su descripción, historia y otros datos relevantes, acompañados de imágenes representativas.

En resumen, ZTEG centraliza la información turística de El Guabo en una plataforma móvil fácil de usar. La aplicación optimiza la experiencia del visitante al permitirle descubrir, identificar y aprender sobre los atractivos de la ciudad de manera fluida, combinando la potencia de la geolocalización con una interfaz de usuario limpia y funcional.

# Ver video Demo
[Ver en Youtube](https://youtu.be/PMSeZMcdUGI)

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.