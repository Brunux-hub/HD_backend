# Plan paso a paso para corregir el backend
## Paso 1: Renombrar PetResponse.petGender → sex
Archivos: PetResponse.java, PetMapper.java

|Actual|Problema|
|---|---|
|Entidad: Pet.sex|Request: PetRequest.sex|
|Response: PetResponse.petGender → JSON: pet_gender|Inconsistente con todo lo demás|

Cambio: Renombrar el field petGender a sex en PetResponse.java. En PetMapper.java, MapStruct mapeará automáticamente Pet.sex → PetResponse.sex por coincidencia de nombre — no se necesita @Mapping adicional.

---

## Paso 2: Renombrar VeterinarianResponse.userResponse → user
Archivos: VeterinarianResponse.java, VeterinarianMapper.java

|Actual|Problema|
|---|---|
|ReceptionistResponse.user → JSON: user|VeterinarianResponse.userResponse → JSON: user_response|

Cambio: Renombrar userResponse a user en VeterinarianResponse.java. En VeterinarianMapper.java, cambiar el mapping de:

@Mapping(target = "userResponse", source = "user")

a:

@Mapping(target = "user", source = "user")

Consecuencia en cadena: AppointmentResponse.veterinarian incluirá un VeterinarianResponse con user en vez de user_response — consumidores existentes deben actualizarse.

---

## Paso 3: Agregar @JsonFormat a fechas
Archivos: PetResponse.java, MedicalHistoryResponse.java (y opcionalmente AppointmentResponse.java)
|Campo|Tipo actual|Serializa como|Debe ser|
|---|---|---|---|
|PetResponse.birthdate|java.util.Date|1742342400000 (timestamp)|"2026-06-23"|
|MedicalHistoryResponse.date|LocalDateTime|"2026-06-23T14:30:00" (OK con JavaTimeModule, pero mejor explícito)|"2026-06-23T14:30:00"|
|AppointmentResponse.date|LocalDateTime|mismo caso|"2026-06-23T14:30:00"|

Cambio en PetResponse.java:

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
Date birthdate

En MedicalHistoryResponse.java y AppointmentResponse.java:

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
LocalDateTime date

---

## Paso 4 (Limpieza): Eliminar imports no usados
- OwnerService.java — importa VeterinarianRequest, VeterinarianResponse, Veterinarian, VeterinarianException sin usarlos
- OwnerController.java — importa ReceptionistRequest, ReceptionistResponse
- PetController.java — importa OwnerRequest, OwnerResponse
- Varios Response DTOs importan @NotBlank, @NotNull de jakarta.validation sin necesitarlos

---

## Paso 5: Actualizar ImplementacionFrontend.md
Una vez aplicados los pasos 1-4, el documento frontend debe reflejar:

|Corrección|Detalle|
|---|---|
|Autenticación|Ya NO decir "no tiene JWT". El login real devuelve {access_token, refresh_token, token_type, expires_in, user}|
|Agregar|POST /auth/refresh (recibe raw string del refresh token)|
|Eliminar|POST /auth/logout (no existe en backend)|
|Snake_case en JSON|id_user, last_names, phone_number, user_response→user (tras paso 2), pet_gender→sex (tras paso 1)|
|LoginResponse|Cambiar de {token, user} a la estructura real|
|Fechas|birthdate como "yyyy-MM-dd", date como "yyyy-MM-dd'T'HH:mm:ss"|

---

## Orden de ejecución sugerido

Paso 1 → Paso 2 → Paso 3 → Compilar y probar → Paso 4 → Paso 5 (doc)

Cada paso es pequeño y atómico. Después de los pasos 1-3, ejecutar mvn compile para verificar que MapStruct genera los mappers correctamente.

## ¿Qué NO tocamos?
|Tema|Decisión|
|---|---|
|POST /auth/logout|No se implementa — requeriría blacklist de tokens. No es crítico para funcionalidad.|
|POST /auth/refresh con @RequestBody String crudo|Funciona, pero es mejorable (anti-patrón). Lo dejamos para otra iteración.|
|@ManyToOne sin cascade|Intencional — evita borrados en cascada accidentales.|
|Endpoints de reportes faltantes|Ya están identificados como "Pendiente". Se agregan cuando se definan los requerimientos.|