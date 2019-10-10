/* global AJAX */

const formulario = document.getElementById("formulario-login");
const loader = document.getElementById("loader");


formulario.addEventListener('submit', e => {
    e.preventDefault();
    loader.classList.add("loader-active");
    let user = username.value;
    let pass = password.value;
    let request = {
        method: "POST",
        url: "login",
        data: `username=${encodeURIComponent(user)}&password=${encodeURIComponent(pass)}`,
        multipart: false
    };
    AJAX.ajax(request).then((response) => {
        switch (response.status) {
            case 200:
                draw(JSON.parse(response.responseText));
                break;
            case 400:
                console.log("Error desconocido " + response.status);
                break;
            default:
                console.log("Error desconocido " + response.status);
                break;
        }
        loader.classList.remove("loader-active");
        //return response;
    });

});

const draw = (json) => {
    let msgError = document.querySelector(".modal-msg");
    let modal;
    if (msgError !== null)document.body.removeChild(msgError);
    if (json.acceso) {
        modal = new ModalMensajeSuccess(json.mensaje, true);
        setTimeout(() => {
            location.href = json.url;
        }, 500);
    } else {
        modal = new ModalMensajeError(json.mensaje, true);
    }
    modal.open("msg-normal-open-text");
};