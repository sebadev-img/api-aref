# Challenge AREF
Rest API para el challenge de AREF. Los datos se obtienen de la api externa [https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com)
### Endpoint Usuarios
Buscar todos los usuarios
```
api/users
```
Buscar usuario por ID
```
api/users/{id_usuario}
```
Buscar usuario por filtro, el filtro puede ser por username, email, o ambos
```
api/users/search?username={nombre_usuario}
```
```
api/userssearch?email={email_usuario}
```
```
api/users/search?username={nombre_usuario}&email={email_usuario}
```
Buscar usuarios por ciudad
```
api/users/cities/{nombre_ciudad}
```
### Endpoint Articulos
Buscar todos los articulos
```
api/posts
```
Buscar articulo por ID
```
api/posts/{id_articulo}
```
Buscar articulos por filtro, el filtro puede ser id de usuario, ocurrencia de un texto en el body, o ambos
```
api/posts/search?userId={id_usuario}
```
```
api/posts/search?bodyText={text_a_buscar}
```
```
api/posts/search?userId={id_usuario}&bodyText={text_a_buscar}
```

### Endpoint Metricas
Buscar total de articulos por usuario
```
api/metrics/users/{id_usuario}
```
Buscar total de comentarios por articulo
```
api/metrics/posts/{id_articulo}
```

### Endpoint Reportes
Top 10 de Articulos con mas comentarios ordenados de mayor a menor
```
api/reports/posts/top
```
