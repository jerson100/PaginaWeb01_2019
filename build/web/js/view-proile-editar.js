/* global ajax, Ajax */

const btnEditar = document.getElementById("user-btn-editar");
const container = document.querySelector("main");
const loader = document.getElementById("loader");

if (btnEditar) {
    btnEditar.addEventListener('click', e => {
        e.preventDefault();
        loader.classList.add("loader-active");
        let request = {
            method: "POST",
            url: "perfil",
            data: `accion=editar`,
            multipart: false
        };

        AJAX.ajax(request).then((response) => {
            container.innerHTML = response.responseText;
            initOyenteTeclado();
            ajaxUpdateProfile();
            loader.classList.remove("loader-active");
            //console.log(response.responseText);
        });

    });
}

