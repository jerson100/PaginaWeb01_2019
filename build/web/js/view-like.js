/*
 * Permite mostrar en una ventana modal a todos los usuarios que dieron like a una determina 
 * publicación.
 * @param {Integer} idPost - Id del post.
 * @returns {undefined}
 * 
 */
const viewUserLike = (idPost,sv=null) => {
    let svg = sv===null?document.getElementById("svg-like"):sv;
    //agregamos el listener
    svg.addEventListener('click', () => {
        callLike(idPost);
    });
};

let callLike = (idPost) => {
    //Realizamos la petición ajax para obtener a todos los 
    //usuarios que dieron like a una determinada publicación.
    let data = new FormData();
    data.append("accion", "detalleLikeXPost");
    data.append("id", idPost);

    let request = {
        method: "post",
        url: "post",
        multipart: true,
        data: data
    };

    let modalObject = mostrarModalAndLoader();

    AJAX.ajax(request)
            .then((response) => {
                return removeLoader(modalObject, response);
            }).then((response) => {
        return drawLike(JSON.parse(response.responseText), modalObject);
    }).catch((error) => {
        modalObject.parent.innerHTML = `<li>${error}<li>`;
    });
};

const removeLoader = (obj, response) => {
    return new Promise((resolve, reject) => {
        obj.parent.removeChild(obj.loader);
        resolve(response);
    });
};

const mostrarModalAndLoader = () => {
    let list = document.createElement("ul");
    list.style.padding = "1rem";
    list.style.minWidth = "150px";
    list.style.minHeight = "150px";

    let loader = document.createElement("div");
    loader.classList.add("loader-container", "loader-active", "loader-especifico");
    list.appendChild(loader);

    let modal = new Modal(null, list, null, true, false);

    modal.open();
    modal.container.firstElementChild.style.maxWidth = "100%";
    modal.container.firstElementChild.style.padding = "0";

    return {"loader": loader, "parent": list};
};

const drawLike = (json = undefined, modalObject) => {
    return new Promise((resolve, reject) => {
        //cuando se de click en el svg, entonces tenemos que 
        //crear un modal y un loader..

        if (json === null || json === undefined || json.allUsers === null) {
            reject("El argumento pasado no es válido");
        }

        if (json.allUsers === undefined)
            reject("Sin likes...");

        //obtener el elemento ul del modal para agregarle los li que contendrán a los usuarios
        //que dieron like..
        let list = modalObject.parent;

        let fragment = document.createDocumentFragment();

        json.allUsers.forEach(e => {
            let li = document.createElement("li");
            li.setAttribute("class", "user-item");
            let adm = "";
            if (e.idTypeUser === 1) {
                adm = `
                            <div class="card-user-type">
                                <svg viewBox="0 0 16 16" class="svg-icon-admin" xmlns="http://www.w3.org/2000/svg"><g transform="translate(-97.103 -44.137)">
                                    <path d="M113.1,52.137a8,8,0,1,0-8,8,8,8,0,0,0,8-8" fill="#f0c419"></path>
                                    <path d="M155.4,88.276l1.7,3.434,3.63.566-2.578,2.673.8,3.549L155.4,96.951,151.847,98.5l.8-3.549-2.578-2.673,3.63-.566Z" transform="translate(-50.299 -41.917)" fill="#ffe69f"></path></g>
                                </svg>
                                <span class="user-type_name">adm</span>
                            </div>
                        `;
            }
            li.innerHTML = `
                        <div class="flex">
                            <div class="card-user">
                                ${adm}
                                <img class="user-img-like" src=${e.url} alt=${e.username}></img>
                            </div>
                            <a href="perfil?id=${e.idPerson}"><span class="us">${e.username}</span></a>
                        </div>
                    `;
            fragment.appendChild(li);
        });

        list.appendChild(fragment);
        resolve();
    });
};
