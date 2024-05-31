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
    if (formularioRegistro.nombre.value.length == 0) {
        alert('El campo nombre es requerido');
        return false;
    }

    if (formularioRegistro.apellido.value.length == 0) {
        alert('El campo apellido es requerido');
        return false;
    }
    expReg = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;

    if (!expReg.test(formularioRegistro.correo.value)) {
        alert('Debe ingresar un correo válido');
        return false;

    }

    if (formularioRegistro.clave.value.trim().length == 0) {
        alert('La contraseña es requerida');
        return false;
    }
    if (formularioRegistro.pais.value == "") {
        alert('El campo país es requerido');
        return false;
    }
    if (formularioRegistro.fecha.value.length == 0) {
        alert('El campo fecha es requerido');
        return false;
    }

    if (!formularioRegistro.terminos.checked) {
        alert('Debe aceptar los terminos y condiciones');
        return false;
    }

}
document.getElementById('formularioLogin').addEventListener('submit', function (event) {
    event.preventDefault();

    if (validarLogin(this)) {
        this.submit();
    }
})

//LOGIN
function validarLogin(formularioLogin) {
    let usuarioReg = formularioLogin.correoRegistrado.value;
    let claveReg = formularioLogin.claveRegistrada.value;


    if (usuarioReg != 'juan@hotmail.com' && claveReg != '1234') {
        alert('Datos incorrectos');
        return false;

    } else {

        window.location = "index.html";
    }
    expReg = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;

    if (!expReg.test(usuarioReg)) {
        alert('Debe ingresar un correo válido');
        return false;

    }
    if (claveReg.trim().length == 0) {
        alert('La contraseña es requerida');
        return false;
    }

}
