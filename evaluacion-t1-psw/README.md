# Evaluación T1 – Pruebas de Software (2424)

Proyecto base: matrícula de cursos (Spring Boot 4.1.0, Java 17, Maven). Base de datos **H2 en memoria**: no necesitas instalar MySQL.

## Cómo ejecutar
1. Abrir la carpeta en IntelliJ IDEA (Open → seleccionar `pom.xml` → Open as Project) y esperar a que Maven descargue las dependencias.
2. Usar **JDK 17** (File → Project Structure → Project SDK).
3. Ejecutar `ModeloT1PswApplication` (la aplicación queda en http://localhost:8080). Los datos de prueba se cargan solos (`config/DataLoader`).
4. Con la aplicación encendida, ejecutar las pruebas de las preguntas 2 y 3. La pregunta 1 (Mockito) no necesita la aplicación encendida.

## Dónde crear tus archivos
- Pregunta 1: `src/test/java/com/pruebas/service/`
- Pregunta 2: `src/test/java/com/pruebas/api/`
- Pregunta 3: `src/test/resources/features/*.feature`, `src/test/java/com/pruebas/bdd/steps/*StepDefinitions.java` y una clase runner en `src/test/java/com/pruebas/bdd/`.

El `pom.xml` ya incluye JUnit 5, Mockito, Rest Assured y Cucumber.
