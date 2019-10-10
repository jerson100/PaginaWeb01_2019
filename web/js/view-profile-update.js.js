'use-strict';
//((window, document, undefined) => {
const ajaxUpdateProfile = () => {
    console.log("click en update");
    let formulario = document.getElementById("formulario-update");

    formulario.addEventListener('submit', e => {
        e.preventDefault();
        loader.classList.add("loader-active");
        console.log(imgprofile);
        let data = new FormData();
        data.append("name", nombreU.value);
        data.append("lastname", apellidoU.value);
        data.append("email",emailU.value);
        data.append("username",usernameU.value);
        data.append("tw", tw.value);
        data.append("ins", ins.value);
        data.append("fb", fb.value);
        data.append("yt", yt.value);
        data.append("img", imgprofile.files[0]);
        data.append("description", description.value);
        data.append("accion", "actualizar");

        let request = {
            method: 'post',
            data: data,
            url: 'perfil',
            multipart: true
        };

        AJAX.ajax(request).then((response) => {
            switch (response.status) {
                    case 200:
                        draw2(JSON.parse(response.responseText));
                        break;
                    case 400:
                        console.log("error: "+response.status);
                        break;
                }
            loader.classList.remove("loader-active");
        }).catch(() => {
            loader.classList.remove("loader-active");
            console.log("error");
        });
    });
};

const draw2 = (json) => {
    let msgError = document.body.querySelector(".modal-msg");
    console.log(msgError);
    console.log(json.estado);
    console.log(json.mensaje);
    console.log(json.url);
    let modal;
    if (msgError !== null)document.body.removeChild(msgError);
    if (json.estado) {
        modal = new ModalMensajeSuccess(json.mensaje, true);
        setTimeout(() => {
            location.href = json.url;
        }, 500);
    } else {
        modal = new ModalMensajeError(json.mensaje, true);
    }
    modal.open("msg-normal-open-text");
}

//})(window, document);
