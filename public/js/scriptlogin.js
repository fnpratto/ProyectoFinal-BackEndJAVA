
//LOGIN
document.getElementById('formularioLogin').addEventListener('submit', function (event) {
    event.preventDefault();

    if (validarLogin(this)) {
        this.submit();
    }
})

function validarLogin(formularioLogin) {
    let usuarioReg = formularioLogin.correoRegistrado.value;
    let claveReg = formularioLogin.claveRegistrada.value;

    if (usuarioReg != 'juan@hotmail.com' && claveReg != '1234') {
        alert('Datos incorrectos');
        return false;
    } else {
        window.location = "../views/admin.html";
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
