const API_SERVER = 'http://localhost:8080/apimovies/apimovies/src/main/java/com/ar/apimovies'; // Change to your API endpoint


// Función para crear elementos HTML
const createElement = (tag, className, attributes = {}) => {
    // Creamos un nuevo elemento HTML del tipo especificado (tag)
    const element = document.createElement(tag);
    
    // Si se especificó una clase, la añadimos al elemento
    if (className) {
        element.classList.add(className);
    }
    
    // Iteramos sobre los atributos pasados como argumento y los añadimos al elemento
    Object.entries(attributes).forEach(([key, value]) => element.setAttribute(key, value));
    
    // Devolvemos el elemento creado
    return element;
};

// Función para cargar películas en la cuadrícula de tendencias
const fetchMoviesGrid = async (page = 1) => {

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // Realizamos una petición fetch a la API para obtener las películas populares
    const response = await fetch(`${API_SERVER}/movie/popular?page=${page}`, options);
    
    // Convertimos la respuesta a JSON
    const data = await response.json();
    
    // Extraemos las películas de la respuesta
    const movies = data.results;

    // Seleccionamos el contenedor de películas de tendencia en el DOM
    const tendenciasContainer = document.querySelector('.peliculasTendencia .peliculas');
    
    // Limpiamos el contenido previo del contenedor
    tendenciasContainer.innerHTML = '';

    // Iteramos sobre cada película obtenida
    movies.forEach(movie => {
        const pelicula = createElement('div', 'pelicula');
        const anchor = createElement('a', '');
        anchor.href = './pages/detalle.html';
        const img = createElement('img', 'imgTendencia', {
            src: `../public/img/${movie.imagen}`, // Assuming images are stored in 'public/img'
            alt: movie.titulo,
            loading: 'lazy'
        });
        const tituloPelicula = createElement('div', 'tituloPelicula');
        const titulo = createElement('h4', '');
        titulo.textContent = movie.titulo;

        tituloPelicula.appendChild(titulo);
        pelicula.append(img, tituloPelicula);
        anchor.appendChild(pelicula);
        const peliculaWrapper = createElement('div', 'peliculas');
        peliculaWrapper.appendChild(anchor);
        tendenciasContainer.appendChild(peliculaWrapper);
    });

    tendenciasContainer.parentElement.setAttribute('data-page', page);
};

// Función para cargar películas en el carrusel de películas aclamadas
const fetchMoviesFlex = async () => {

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    try {
        // Fetch top-rated movies from your API
        const response = await fetch(`${API_SERVER}/movies/top_rated`, options);
        
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        // Convert the response to JSON
        const data = await response.json();
        
        // Extract movies from the response
        const movies = data.results;

        // Select the container for top-rated movies in the DOM
        const aclamadasContainer = document.querySelector('.aclamadas');
        
        // Clear previous content in the container
        aclamadasContainer.innerHTML = '';

        // Iterate over each movie and create HTML elements to display it
        movies.forEach(movie => {
            // Create HTML elements for the movie
            const peliculaItem = createElement('div', 'peliculaItem');
            const img = createElement('img', 'imgAclamada', {
                src: `../public/img/${movie.imagen}`, // Assuming images are stored in 'public/img'
                alt: movie.titulo,
                loading: 'lazy'
            });
            
            // Add the elements to the DOM
            peliculaItem.appendChild(img);
            aclamadasContainer.appendChild(peliculaItem);
        });
    } catch (error) {
        console.error('Error fetching top-rated movies:', error);
    }
};

// Event listener para el botón "Anterior"
document.querySelector('.anterior').addEventListener('click', () => {
    // Obtener el número de página actual
    let currentPage = Number(document.querySelector('.peliculasTendencia').getAttribute('data-page'));
    // Si es la primera página, no hacemos nada
    if (currentPage <= 1) return;
    // Cargar las películas de la página anterior
    fetchMoviesGrid(currentPage - 1);
});

// Event listener para el botón "Siguiente"
document.querySelector('.siguiente').addEventListener('click', () => {
    // Obtener el número de página actual
    let currentPage = Number(document.querySelector('.peliculasTendencia').getAttribute('data-page'));
    // Cargar las películas de la página siguiente
    fetchMoviesGrid(currentPage + 1);
});

// Ejecutamos las funciones de carga de películas al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    // Cargamos las películas en la cuadrícula de tendencias
    fetchMoviesGrid();
    // Cargamos las películas en el carrusel de películas aclamadas
    fetchMoviesFlex();
});




