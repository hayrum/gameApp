Cree una aplicación demo de una lista de juegos, en el cual hace varias funcionalidades básica:
1.- Realiza por primera vez, un consumo de rest api con Retrofit en el cual una vez obtenida la respuesta de la API, almacenamos una lista de juegos obtenida de dicha API.
2.- Podemos interactuar con la lista, teniendo la oportunidad de filtra por medio de una barra buscadora en la cual, se filtra por (categoría y título) del juego.
3.- Al dar click en el juego de la lista, podemos ver una detalle breve del juego en el cual, también obtenemos los valores a editar y hasta abajo tenemos el botón para poder realizar una actualización de cualquier descripción del juego.
4 .- Si nos posicionamos en la lista, podremos ver un icono de basura el cual describe su funcionalidad como (eliminar), el cual al dar click, eliminamos el juego de la base de datos, por ende, se elimina de nuestra lista actual.

NOTA: Hasta que nos quedemos sin juegos o al eliminar la aplicación al volverla instalar, volverá a consumir el mismo servicio de obtener todos los juegos nuevamente, tal cual se solicitó en dicha funcionalid principal.
