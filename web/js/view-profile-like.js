"use strict";

((window, document, undefined) => {
    let container = document.getElementById("container-publicaciones");
    let loader = document.getElementById("loader");
    container.addEventListener("click", e => {
        if (e.target.tagName === "path") {
            loader.classList.add("loader-active");
            let svg = e.target.parentElement;
            let idPost = svg.nextElementSibling.textContent;
            let dat = new FormData();
            dat.append("id", idPost);
            dat.append("accion", "ver")
            let request = {
                method: 'POST',
                url: 'post',
                data: dat,
                multipart: true
            };
            AJAX.ajax(request)
                    .then((response) => {
                        switch (response.status) {
                            case 404:
                                break;
                            case 200:
                                draw(JSON.parse(response.responseText),svg,svg.previousElementSibling);
                                break;
                        }
                        loader.classList.remove("loader-active");
                    })
                    .catch(() => {
                        loader.classList.remove("loader-active");
                    });
        }
    });

    const draw = (json,svg,like) => {
        let msgError = document.body.querySelector(".modal-msg");
        console.log(msgError);
        console.log(json.estado);
        console.log(json.mensaje);
        console.log(json.url);
        console.log(svg);
        console.log(like);
        let modal;
        if (msgError !== null)
            document.body.removeChild(msgError);
        if (json.estado) {
            modal = new ModalMensajeSuccess(json.mensaje, true);
            like.textContent = parseInt(like.textContent) + 1;
            svg.style.fill = "#007BDF";
        } else {
            modal = new ModalMensajeError(json.mensaje, true);
            if(json.url){
                setTimeout(() => {
                   location.href = json.url;
                }, 800);
            }
        }
        modal.open("msg-normal-open-text");
    };

})(window, document);
