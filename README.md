
# Conversor de Monedas (Java + Swing)

Una aplicación de escritorio desarrollada en Java con interfaz gráfica (Swing) que permite convertir montos entre distintas monedas usando una API de tipo de cambio en tiempo real.

## 📸 Captura de pantalla

> *(Agrega aquí una imagen si deseas, como una screenshot del programa funcionando)*

## 🚀 Características

- Conversión entre USD, EUR, BOB, BRL y ARS.
- Interfaz gráfica amigable construida con Java Swing.
- Consulta de tasas de cambio actualizadas desde exchangerate-api.com.
- Conversión rápida y precisa.

## 💻 Requisitos

- Java 11 o superior
- Maven
- Conexión a Internet

## ⚙️ Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/marquinmoj/ConversorMonedasGUI.git
cd ConversorMonedasGUI
```

2. Compila el proyecto con Maven:

```bash
mvn clean install
```

3. Ejecuta la aplicación:

```bash
mvn exec:java -Dexec.mainClass="com.conversor.Main"
```

## 🔐 API

Se utiliza [ExchangeRate-API](https://www.exchangerate-api.com/). Necesitas tu propia API Key para que funcione correctamente.

Reemplaza en el código esta línea:

```java
String url = "https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/USD";
```

por:

```java
String url = "https://v6.exchangerate-api.com/v6/TU_CLAVE_REAL/latest/USD";
```

## 👨‍💻 Autor

- Marcelo Mojica
- [GitHub](https://github.com/marquinmoj)
