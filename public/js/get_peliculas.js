document.addEventListener('DOMContentLoaded', async () => {
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };
    const response = await fetch('http://localhost:8080/apimovies/apimovies/src/main/java/com/ar/apimovies', options);
    const data = await response.json();

    const movies = data;

    const tbody = document.getElementById('tbodyMovies');

    movies.forEach(movie => {
        const tr = document.createElement('tr');
        const titulo = document.createElement('td');
        titulo.textContent = movie.titulo;

        const fechaLanzamiento = document.createElement('td');
        fechaLanzamiento.textContent = movie.fechaLanzamiento;

        const genero = document.createElement('td');
        genero.textContent = movie.genero;

        const duracion = document.createElement('td');
        duracion.textContent = movie.duracion;

        const reparto = document.createElement('td');
        reparto.textContent = movie.reparto;

        const sinapsis = document.createElement('td');
        sinapsis.textContent = movie.sinapsis;

        const director = document.createElement('td');
        director.textContent = movie.director;

        const imagen = document.createElement('td');
        const img = document.createElement('img');

        img.src = "../public/img/" + movie.imagen;
        img.width = '150';
        img.alt = movie.Nombre;
        imagen.appendChild(img);

        img.classList.add('img-fluid');
        img.classList.add('img-thumbnail');

        tr.appendChild(titulo);
        tr.appendChild(fechaLanzamiento);
        tr.appendChild(genero);
        tr.appendChild(duracion);
        tr.appendChild(reparto);
        tr.appendChild(sinapsis);
        tr.appendChild(director);
        tr.appendChild(imagen);

        tbody.appendChild(tr);

    });
});