/* global ajax, Ajax */

const btnEditar = document.getElementById("user-btn-editar");
const container = document.querySelector("main");
const loader = document.getElementById("loader");

btnEditar.addEventListener('click', e => {
    e.preventDefault();
    loader.classList.add("loader-active");
    let request = {
        method: "GET",
        url: "perfil?accion=editar"
    };

    AJAX.ajax(request).then((response) => {
        container.innerHTML = response.responseText;
        initOyenteTeclado();
        loader.classList.remove("loader-active");
        //console.log(response.responseText);
    });

});
