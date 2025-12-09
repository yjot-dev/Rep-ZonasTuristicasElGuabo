# App Turismo El Guabo (ATEG)
Esta app permite navegar por los principales centros turísticos de 
El Guabo, utilizando la API de Google Maps, en el mapa se indican 
las ubicaciones y si se da un clic sobre alguna de ellas se muestra 
el nombre del lugar y si se da doble clic se abre una nueva ventana 
con su descripción.

# Características principales
- 🪟 Interfaz clasica con XML
- 📊 Integración con ViewModel + StateFlow
- 🎨 Patrón de diseño arquitectónico con MVVM
- 🧩 Inyección de dependencias con Hilt
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
- Al abrir la app, se muestra un mapa en donde el usuario podra elegir 
un destino turistico de la ciudad de El Guabo.
- En Mapa, el usuario vera una serie de marcadores de lugares en su 
alrededor, en la cual podra hacer click para ver su informacion.
- En Info, es la vista que muestra la informacion del lugar turistico 
seleccionado luego de haber hecho click en el marcador del mapa.

# Ver video Demo
No disponible aun

# Contribución
- Haz un fork del repositorio
- Crea una rama con tu feature: git checkout -b feature/nueva-funcionalidad
- Haz commit de tus cambios: git commit -m "Agrega nueva funcionalidad"
- Haz push a la rama: git push origin feature/nueva-funcionalidad
- Abre un Pull Request

# Licencia
Este proyecto está bajo la licencia GPL-3.0. Consulta el archivo LICENSE para más detalles.