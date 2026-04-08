const descripciones = {
    neural: "La Terapia Neural es un tratamiento que utiliza anestésicos locales para restablecer el flujo eléctrico del sistema nervioso autónomo, aliviando dolores crónicos y mejorando la función orgánica.",
    quiro: "La Quiropraxia se enfoca en el diagnóstico y tratamiento de los trastornos del sistema musculoesquelético, especialmente de la columna vertebral, mediante ajustes manuales precisos.",
    fisio: "La Fisioterapia emplea técnicas físicas como ejercicios, masajes y electroterapia para rehabilitar lesiones, recuperar la movilidad y prevenir discapacidades funcionales.",
    nutricion: "La Nutrición y Dietética Terapéutica diseña planes alimenticios personalizados para tratar enfermedades crónicas, mejorar el metabolismo y promover el bienestar general.",
    otras: "Contamos además con especialidades como Acupuntura, Homeopatía, Medicina Ayurvédica y Terapia Floral, orientadas al equilibrio integral del paciente."
};

function mostrarDescripcion(especialidad) {
    const contenedor = document.getElementById("descripcion-especialidad");
    const texto = document.getElementById("texto-descripcion");
    texto.textContent = descripciones[especialidad];
    contenedor.style.display = "block";
}

function validarCampoObligatorio(campo, errorElement, mensaje) {
    if (campo.value.trim() === '') {
        errorElement.textContent = mensaje;
        return false;
    } else {
        errorElement.textContent = '';
        return true;
    }
}

function validarLongitud(campo, errorElement, min, max, mensaje) {
    if (campo.value.length < min || campo.value.length > max) {
        errorElement.textContent = mensaje;
        return false;
    } else {
        errorElement.textContent = '';
        return true;
    }
}

function validarCorreo(campo, errorElement, mensaje) {
    const correoRegex = /^[a-zA-Z0-9._%+-]+@unicauca\.edu\.co$/;
    if (!correoRegex.test(campo.value)) {
        errorElement.textContent = mensaje;
        return false;
    } else {
        errorElement.textContent = '';
        return true;
    }
}

function validarGenero(genero, errorElement, mensaje) {
    let seleccionado = false;
    for (let i = 0; i < genero.length; i++) {
        if (genero[i].checked) {
            seleccionado = true;
            break;
        }
    }

    if (!seleccionado) {
        errorElement.textContent = mensaje;
        return false;
    } else {
        errorElement.textContent = '';
        return true;
    }
}

function validarContrasenas(campo1, campo2, errorElement, mensaje) {
    if (campo1.value !== campo2.value || campo1.value.trim() === '' || campo2.value.trim() === '') {
        errorElement.textContent = mensaje;
        return false;
    } else {
        errorElement.textContent = '';
        return true;
    }
}

function mostrarMensajeExito() {
    Toastify({
        text: "✅ ¡Registro exitoso!",
        duration: 3000,            // Duración: 3 segundos
        gravity: "top",             // Posición: arriba
        position: "right",          // Alineación: derecha
        style: {
            background: "rgba(0, 128, 0, 0.8)",  // Verde con transparencia
            color: "#fff",                      // Texto blanco
            borderRadius: "12px",               // Esquinas redondeadas
            boxShadow: "0 4px 8px rgba(0, 0, 0, 0.3)", // Sombra ligera
            padding: "12px 20px"               // Más relleno
        },
        stopOnFocus: true, // No desaparecer al pasar el mouse
    }).showToast();
}

// Función principal que valida todo el formulario
function validarFormulario() {

    const inputTipoIdentificacion = document.getElementById('identificacion');
    const inputIdentificacion = document.getElementById('numero-identificacion');
    const inputNombres = document.getElementById('nombres');
    const inputApellidos = document.getElementById('apellidos');
    const inputCorreoElectronico = document.getElementById('correo-electronico');
    const inputGenero = document.getElementsByName('genero');

    const labelErrorTipoIdentificacion = document.getElementById('errorTipoIdentificacion');
    const labelErrorNumeroIdentificacion = document.getElementById('errorNumeroIdentificacion');
    const labelErrorNombres = document.getElementById('errorNombres');
    const labelErrorApellidos = document.getElementById('errorApellidos');
    const labelErrorCorreo = document.getElementById('errorCorreo');
    const labelErrorGenero = document.getElementById('errorGenero');

    const tipoIdentificacionValida = validarCampoObligatorio(inputTipoIdentificacion, labelErrorTipoIdentificacion, "El tipo de identificación es obligatorio");
    const identificacionValida = validarCampoObligatorio(inputIdentificacion, labelErrorNumeroIdentificacion, 'La identificación es obligatoria');
    const nombresValidos = validarLongitud(inputNombres, labelErrorNombres, 1, 20, 'El nombre debe tener entre 1 y 20 caracteres');
    const apellidosValidos = validarLongitud(inputApellidos, labelErrorApellidos, 1, 20, 'El apellido debe tener entre 1 y 20 caracteres');
    const correoValido = validarCorreo(inputCorreoElectronico, labelErrorCorreo, 'El correo debe tener el dominio @unicauca.edu.co');
    const generoValido = validarGenero(inputGenero, labelErrorGenero, 'El género es obligatorio');

    // Si todas las validaciones son correctas, se devuelve true y se puede enviar el formulario al servidor
    if (tipoIdentificacionValida && identificacionValida && nombresValidos && apellidosValidos && correoValido && generoValido) {
        mostrarMensajeExito();
        const formulario = document.getElementById('formularioContacto');
        formulario.scrollIntoView({ behavior: "smooth", block: "start" });
        formulario.classList.add("was-validated");
        setTimeout(() => {
            formulario.reset();
        }, 2000);
        return false; // Evita el envío del formulario
    } else {

        alert('Por favor, complete correctamente el formulario.');
        return false; // Bloquea el envío del formulario
    }
}

function validarCamposAlCambiarFoco() {
    const campoTipoIdentificacionReg = document.getElementById('identificacionReg');
    const campoNumeroIdentificacionReg = document.getElementById('numeroIdentificacionReg');
    const campoNombresReg = document.getElementById('nombresReg');
    const campoApellidosReg = document.getElementById('apellidosReg');
    const campoCorreoReg = document.getElementById('correoReg');
    const campoGeneroReg = document.getElementsByName('generoReg');
    const campoPasswordReg = document.getElementById('passwordReg');
    const campoPasswordConfReg = document.getElementById('passwordConfReg');

    const errorTipoIdentificacionReg = document.getElementById('errorTipoIdentificacionReg');
    const errorNumeroIdentificacionReg = document.getElementById('errorNumeroIdentificacionReg');
    const errorNombresReg = document.getElementById('errorNombresReg');
    const errorApellidosReg = document.getElementById('errorApellidosReg');
    const errorCorreoReg = document.getElementById('errorCorreoReg');
    const errorGeneroReg = document.getElementById('errorGeneroReg');
    const errorPasswordReg = document.getElementById('errorPasswordReg');

    const tipoIdentificacionValidaReg = validarCampoObligatorio(
        campoTipoIdentificacionReg,
        errorTipoIdentificacionReg,
        "El tipo de identificación es obligatorio"
    );

    const numeroIdentificacionValidaReg = validarCampoObligatorio(
        campoNumeroIdentificacionReg,
        errorNumeroIdentificacionReg,
        "El número de identificación es obligatorio"
    );

    const nombresValidosReg = validarLongitud(
        campoNombresReg,
        errorNombresReg,
        1,
        20,
        "El nombre debe tener entre 1 y 20 caracteres"
    );

    const apellidosValidosReg = validarLongitud(
        campoApellidosReg,
        errorApellidosReg,
        1,
        20,
        "El apellido debe tener entre 1 y 20 caracteres"
    );

    const correoValidoReg = validarCorreo(
        campoCorreoReg,
        errorCorreoReg,
        "El correo debe tener el dominio @unicauca.edu.co"
    );

    const generoValidoReg = validarGenero(
        campoGeneroReg,
        errorGeneroReg,
        "El género es obligatorio"
    );

    const passwordValidoReg = validarContrasenas(
        campoPasswordReg,
        campoPasswordConfReg,
        errorPasswordReg,
        "Las contraseñas no coinciden"
    );

    if (
        tipoIdentificacionValidaReg &&
        numeroIdentificacionValidaReg &&
        nombresValidosReg &&
        apellidosValidosReg &&
        correoValidoReg &&
        generoValidoReg &&
        passwordValidoReg
    ) {

        mostrarMensajeExito();

        const formularioRegistroReg = document.getElementById('formularioRegistro');
        formularioRegistroReg.scrollIntoView({ behavior: "smooth", block: "start" });
        formularioRegistroReg.classList.add("was-validated");

        setTimeout(() => {
            formularioRegistroReg.reset();
        }, 2000);

        return false;

    } else {
        alert('Por favor, complete correctamente el formulario.');
        return false;
    }
}

