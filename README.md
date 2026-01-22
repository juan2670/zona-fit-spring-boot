# 🏋️ Zona Fit — Gestión de Clientes

![JAVA](https://img.shields.io/badge/JAVA-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING BOOT](https://img.shields.io/badge/SPRING%20BOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MYSQL](https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JPA](https://img.shields.io/badge/SPRING%20DATA%20JPA-0B7A75?style=for-the-badge)
![STATUS](https://img.shields.io/badge/STATUS-TERMINADO-2ea44f?style=for-the-badge)

Sistema simple de administración para gimnasios (gestión de **clientes**) desarrollado en **Java** con **Spring Boot** y persistencia en **MySQL**.

---

## ⚡ Descripción del Proyecto

**Zona Fit** es una aplicación **de consola** (CLI) creada para gestionar el ciclo de vida de los **clientes** de un gimnasio.  
El proyecto usa **Spring Boot** en modo *non-web* y organiza el código por capas para mantenerlo claro y mantenible.

Incluye un **menú interactivo** que permite ejecutar operaciones sobre clientes, guardando la información en **MySQL** mediante **Spring Data JPA**.

---

## ✨ Características Principales

- 📁 **Gestión de Clientes desde Consola (Menú):**
  - 1️⃣ Mostrar lista de clientes  
  - 2️⃣ Buscar cliente por ID  
  - 3️⃣ Agregar nuevo cliente  
  - 4️⃣ Eliminar cliente por ID  
  - 5️⃣ Salir  

- 🗃️ **Persistencia en Base de Datos (MySQL):**
  - Entidad `Cliente` con campos: `id`, `nombre`, `apellido`, `membresia`.
  - Operaciones de BD con `JpaRepository` (Spring Data JPA).

- 🛡️ **Validaciones y Control de Errores:**
  - Validación con **Regex** para `nombre` y `apellido` (no permite números ni símbolos).
  - Validación de valores numéricos **mayores a 0** para ID y membresía.
  - Manejo de excepciones (**try/catch**) para evitar que el programa se caiga por entradas inválidas.

- 🧾 **Logs Limpios en Consola:**
  - Registro de eventos con **SLF4J + Logback** (mensajes claros durante la ejecución).

---

## 🧱 Estructura del Proyecto (Arquitectura)

- **Modelo** → `Cliente` (Entidad JPA)
- **Repositorio** → `ClienteRepositorio` (`JpaRepository`)
- **Servicio** → `IClienteServicio` / `ClienteServicio` (lógica de negocio)
- **Aplicación / Consola** → `ZonaFitApplication` (menú e interacción con el usuario)

---

## 🖥️ Vista del Menú (Referencia)

```text
****** Menu zona_fit ******
1. Mostar lista de clientes
2. Buscar cliente por ID
3. Agregar nuevo cliente
4. Eliminar cliente por ID
5. Salir
Seleccione una opcion:
