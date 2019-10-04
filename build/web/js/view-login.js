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
        data: `username=${encodeURIComponent(user)}&password=${encodeURIComponent(pass)}`
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
    if (json.acceso) {
        location.href = json.url;
    } else {
        let msgError = document.querySelector(".login-msg");
        if (!msgError) {
            let documentFrag = document.createDocumentFragment();
            let container = document.createElement("div");
            container.classList.add("login-msg", "active");
            container.textContent = json.mensaje;
            documentFrag.appendChild(container);
            document.body.appendChild(documentFrag);
            iniMsgError(container)
            .then((container)=>{
                    return finMsgError(container);
               })
            .then();
        }
    }
};

const iniMsgError = (container)=>{
    return new Promise((resolve,reject)=>{
        console.log("mensaje activo");
        setTimeout(() => {
            console.log("mensaje activo quitado");
            container.classList.remove("active");
            container.classList.add("msgError-desactive");
            resolve(container);
        },1000);            
    });
}

const finMsgError = (container)=>{
    return new Promise((resolve,reject)=>{
        console.log("mensaje retrocediendo");
        setTimeout(() => {
            console.log("mensaje eliminado");
            document.body.removeChild(container);
            resolve();
        },500);
    });
}