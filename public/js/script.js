// API

const container = document.getElementById('imageContainer')

fetch('https://rickandmortyapi.com/api/character')
    .then(response => response.json())
    .then(datos => {
        datos.results.forEach(element => {

            const div = document.createElement('div');
            const img = document.createElement('img')
            img.src = element.image;
            img.alt = element.name;
            img.style.width = '200px';
            const p = document.createElement('p');
            p.textContent = element.name;
            div.appendChild(img);
            div.appendChild(p);
            container.appendChild(div);
        })
    })
    .catch(err => console.log(err))

// REGISTRO

document.getElementById('formularioRegistro').addEventListener('submit', function (event) {
    event.preventDefault();

    if (validar(this)) {
        this.submit();
    }
})
function validar(formularioRegistro) {
    expReg = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    let nombreValido = formularioRegistro.nombre.value.length != 0;
    let apellidoValido = formularioRegistro.apellido.value.length != 0;
    let correoValido = expReg.test(formularioRegistro.correo.value);
    let claveValida = formularioRegistro.clave.value.trim().length != 0;
    let paisValido = formularioRegistro.pais.value != "";
    let fechaValida = formularioRegistro.fecha.value.length != 0;
    let terminosValidos = formularioRegistro.terminos.checked;

    if (nombreValido && apellidoValido && correoValido && claveValida && paisValido && fechaValida && terminosValidos) {
        window.location = "../index.html";
    } else {
        if (!nombreValido) {
            alert('El campo nombre es requerido');
            return false;
        }

        if (!apellidoValido) {
            alert('El campo apellido es requerido');
            return false;
        }

        if (!correoValido) {
            alert('Debe ingresar un correo válido');
            return false;

        }
        if (!claveValida) {
            alert('La contraseña es requerida');
            return false;
        }

        if (!paisValido) {
            alert('El campo país es requerido');
            return false;
        }

        if (!fechaValida) {
            alert('El campo fecha es requerido');
            return false;
        }

        if (!terminosValidos) {
            alert('Debe aceptar los terminos y condiciones');
            return false;
        }
    }


}