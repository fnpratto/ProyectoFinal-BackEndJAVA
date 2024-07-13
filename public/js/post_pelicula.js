
document.addEventListener('DOMContentLoaded', async () => {

    
   
    formNuevaPelicula = document.getElementById('formNuevaPelicula');


    // Agrego el evento submit al formulario
    // Añadimos un evento que se dispara cuando el formulario se envía.
    // La función que se pasa al listener se ejecutará cada vez que el formulario se envíe.
    formNuevaPelicula.addEventListener('submit', async (event) => {

        // Prevenimos el comportamiento por defecto del formulario
        // Esto evita que el formulario se envíe de la manera tradicional y recargue la página.
        event.preventDefault();

        // Obtener los datos del formulario:
        // Aquí, usamos FormData para extraer los valores de los campos del formulario.
        const titulo = document.getElementById('titulo').value;
        const fechaLanzamiento = document.getElementById('fechaLanzamiento').value;
        const genero = document.getElementById('genero').value;
        const duracion = document.getElementById('duracion').value;
        const reparto = document.getElementById('reparto').value;
        const sinapsis = document.getElementById('sinapsis').value;
        const director = document.getElementById('director').value;
        const imagen = document.getElementById('imagen').value;

        // Validar los datos del formulario:
        // Verificamos que todos los campos obligatorios tienen un valor.
        // Si alguno está vacío, mostramos una alerta y detenemos el procesamiento.
        if (titulo === '') {
            alert('Titulo es obligatorio');
            return;
        }

        const movie = {
            titulo,
            fechaLanzamiento,
            genero,
            duracion,
            reparto,
            sinapsis,
            director,
            imagen
        };

        // Preparar los datos para el envío:
        // levanto solo el nombre del file para enviarlo a la api
        // Esta línea simplemente extrae el nombre del archivo seleccionado por el usuario 
        // en el campo de tipo file del formulario y lo guarda en la variable 
        // imageName para su uso posterior en la solicitud HTTP.
        const imageName = imagen.name;
      
        //configuracion de options, que contiene los detalles de la solicitud POST,
        // incluyendo el método, las cabeceras y el cuerpo. 
        // El cuerpo es un objeto JSON que se convierte en una cadena con JSON.stringify.
        try {
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(movie)
            };
            const response = await fetch('http://localhost:8080/apimovies/apimovies/src/main/java/com/ar/apimovies', options);
            
            if (response.ok) {
                console.log('Pelicula agregada exitosamente');
                // Optionally, you can refresh the movie list or reset the form
                form.reset();
                // Refresh the movie list by calling the function to fetch and display movies
                // fetchMovies();
            } else {
                console.error('Error al agregar la pelicula');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });

});