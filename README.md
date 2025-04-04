# InventarioDB 📦📱

**InventarioDB** es una aplicación Android desarrollada en Java para la gestión básica de productos en un inventario. Esta app permite visualizar, registrar y manejar productos usando una base de datos local.

## 🧩 Características

- Interfaz sencilla y amigable.
- Registro de productos con nombre, cantidad y precio.
- Almacenamiento local con SQLite (implementado en `dbProduct.java`).
- Diseño responsivo compatible con múltiples resoluciones de pantalla.

## 🏗️ Estructura del Proyecto

```
InventarioDB/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/inventariodb/
│   │   │   │   ├── MainActivity.java         # Pantalla principal de la app
│   │   │   │   ├── dbProduct.java            # Clase de acceso a la base de datos
│   │   │   │   └── Product.java              # Modelo de producto
│   │   │   ├── res/
│   │   │   │   ├── layout/activity_main.xml  # Layout de la interfaz
│   │   │   │   └── mipmap/ & drawable/       # Recursos gráficos e íconos
│   ├── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
```

## ▶️ Cómo Ejecutar el Proyecto

### Prerrequisitos

- Android Studio (recomendado Arctic Fox o superior).
- SDK de Android 31 o superior.
- JDK 11+.

### Instrucciones

1. Clona este repositorio:

   ```bash
   git clone [https://github.com/tu-usuario/InventarioDB.git](https://github.com/Marlon-Trujillo-Jaramillo/RepositorioDb.git)
   ```

2. Abre el proyecto en Android Studio.

3. Espera a que Gradle sincronice todas las dependencias.

4. Ejecuta el proyecto en un emulador o dispositivo físico.

## 📂 Archivos Clave

- `MainActivity.java`: Actividad principal, incluye la lógica de interfaz.
- `dbProduct.java`: Clase helper para conexión y operaciones CRUD con SQLite.
- `Product.java`: Clase modelo que representa un producto.
- `activity_main.xml`: Layout XML que define la interfaz del usuario.


## ✍️ Créditos

Desarrollado por Marlon Trujillo

