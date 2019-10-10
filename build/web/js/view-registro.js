/* global Ajax, AJAX, openMensajeModal, openMensajeModal2 */

const formulario = document.getElementById("formulario-registro");
const loader = document.getElementById("loader");

formulario.addEventListener('submit', e => {
    e.preventDefault();
    loader.classList.add("loader-active");

    let name = document.getElementById("name").value;
    let lastname = document.getElementById("last-name").value;
    let email = document.getElementById("email").value;
    let username = document.getElementById("username").value;
    let pass = document.getElementById("pass").value;
    let pass_r = document.getElementById("pass-r").value;

    let request = {
        method: 'POST',
        url: 'registro',
        data: `accion=registrar_usuario&name=${name}&last-name=${lastname}&email=${email}&username=${username}&pass=${pass}&passR=${pass_r}`,
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
    });


});

const draw = (json) => {
    let msg = document.querySelector(".modal-msg");
    let modal;
    if (msg !== null)document.body.removeChild(msg);
    if (json.creado) {
        modal = new ModalMensajeSuccess(json.mensaje);
        modal.open("msg-normal-open-text");
        clearInput();
        redirect(modal.container.firstElementChild.firstElementChild)
                .then(() => {
                    location.href = json.url;
                });
    } else {
        modal = new ModalMensajeError(json.mensaje, true);
        modal.open("msg-normal-open-text");
    }
};
        const redirect = (container) => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            container.textContent = "redireccionando al login";
            resolve();
        }, 3000);
    });
}

const clearInput = () => {
    document.getElementById("name").value = "";
    document.getElementById("last-name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("username").value = "";
    document.getElementById("pass").value = "";
    document.getElementById("pass-r").value = "";
};

