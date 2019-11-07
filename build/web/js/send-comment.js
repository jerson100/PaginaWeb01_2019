/*
 * @param {Integer} idPost - Id de la publicación 
 * @param {Object} send - Elemento html
 * @returns {undefinied}
 * 
 */
const sendComment = (element,comment, idPost, countComment, elementtoReceive) => {
    element.addEventListener('click', e => {
        if(comment.value.localeCompare("") !== 0){
            let dat = new FormData();
            let loader = createLoader();
            let comm = comment.value;
            comment.value = "";
            dat.append("accion", "comment");
            dat.append("id", idPost);
            dat.append("texto", comm);
            let request = {
                method: 'Post',
                url: 'post',
                multipart: true,
                data: dat
            };
            AJAX.ajax(request)
                    .then((response) => openModalError(JSON.parse(response.responseText),loader))
                    .then((response) => {
                        document.body.removeChild(response.container);
                        location.href = response.data.url;
                    })
                    .catch((dat) => {//cuando url es vacío
                        printComment(dat.data, comm, countComment, elementtoReceive);
                    });
        }
    });
};

const createLoader = ()=>{
    let loader = document.createElement("div");
    loader.classList.add("loader-container", "loader-active");
    document.body.appendChild(loader);
    return loader;
}

const printComment = (json, texto, countComment, elementtoReceive) => {

    if (json.estado) {

        //actualizamos la cantidad de comentarios + 1
        let container_count = countComment;
        let array = container_count.textContent.split(/[ ]+/);
        console.log(array);
        container_count.textContent = parseInt(array[0]) + 1 + " " + array[1];

        let us = json.user;
        let container = elementtoReceive;
        let fragment = document.createDocumentFragment();
        let inner = `
                        <div class="comment-main-level" id="comment-${us.idComentario}">
                            <!-- Avatar -->
                            <div class="card_autor_img comment-avatar">
                                
                                <div class="card-user-type"><svg viewBox="0 0 16 16" class="svg-icon-admin" xmlns="http://www.w3.org/2000/svg"><g transform="translate(-97.103 -44.137)">
                                                        <path d="M113.1,52.137a8,8,0,1,0-8,8,8,8,0,0,0,8-8" fill="#f0c419"></path>
                                                        <path d="M155.4,88.276l1.7,3.434,3.63.566-2.578,2.673.8,3.549L155.4,96.951,151.847,98.5l.8-3.549-2.578-2.673,3.63-.566Z" transform="translate(-50.299 -41.917)" fill="#ffe69f"></path></g>
                                                        </svg>
                                                        <span class="user-type_name">adm</span>
                                                    </div>
                                <img class="" src=${us.url} alt=${us.username}">
                            </div>
                                                    
                            <!-- Contenedor del Comentario -->
                            <div class="comment-box">
                                <div class="comment-head">
                                    <h6 class="comment-name by-author"><a href=perfil?id=${us.idPerson}>${us.username}</a></h6>
                                        <span>Hace instantes</span>
                                        <i class="fa fa-heart"></i>
                                </div>
                                <div class="comment-content">
                                    ${texto}
                                </div>
                                <button class="response button" style="
                                                                        color: white;
                                                                        background: #03658c;
                                                                        background: background: #03658c;
                                                                        /* padding: 12px; */
                                                                        margin-left: 12px;
                                                                        margin-bottom: 12px;
                                                                         ">
                                                                            responser
                                </button>
                             </div>
                        </div>
                `;

        let item = createCustomElement("li", {"class": "list-level"}, [inner]);
        item.classList.add("opacityBorderActive");
        fragment.appendChild(item);

        container.appendChild(fragment);
        let parent = container.parentElement.parentElement;
        parent.scrollTo(0, parent.scrollHeight);
        setInterval(() => {
            item.classList.remove("opacityBorderActive");
        }, 3000);

    }

};


/*
 * Nos permite mostrar un modal con un mensaje de error
 * en el viewport
 * @param {Object} json
 * @returns {undefined}
 * 
 */
const openModalError = (json,loader) => {
    document.body.removeChild(loader);
    return new Promise((resolve, reject) => {
        if (json === null ||
                json === undefined ||
                json.mensaje === null ||
                json.url === null ||
                json.url === '') {
            reject({"data": json, "msg": "Error"});
        } else {
            let modalM = new ModalMensajeError(json.mensaje, true);
            modalM.open("msg-normal-open-text");//abrimos el modal
            setTimeout(() => {
                resolve({"container": modalM.container, "data": json});
            }, 2000);
        }
    });
};
