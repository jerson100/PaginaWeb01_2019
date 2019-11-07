/* global AJAX */
'use strict';
((document, window, undefined) => {

    const openModalImg = (p) => {

        let padre = document.getElementById(p);

        if (padre) {
            padre.addEventListener('click', e => {
                let elementP = e.target;//elemento pulsado
                
                if (elementP.tagName === 'IMG') {
                    
                    let idPost = e.target.nextElementSibling.lastElementChild.textContent;
                    let prev = location.href;
                    console.log(location.href);
                    history.pushState(null, "", `post?id=${idPost}`);
                    console.log(location.href);
                    if (document.querySelector(".container-details") !== null) {
                        document.body.removeChild(document.querySelector(".container-details"));
                    }

                    let footer = elementP.parentElement.nextElementSibling;

                    let container_img = createCustomElement("div", {"class": "modal-imagen", "style": "display_flex"}, []);

                    container_img.innerHTML = `
                    <div class="containerImg">
                        <img class="modal-img" src=${elementP.src} alt=${elementP.alt}>
                        <!--<div class="container-details">
                            <img src=${e.target.parentElement.parentElement.querySelector(".card_autor_img").src} class="img-user-post">
                            <div class="post-details-username">
                                <span>${elementP.previousElementSibling.firstElementChild.textContent}</span>
                            </div>
                            <div class="post-details-date">
                            </div>
                        </div>-->
                    </div>
                    <div class="containerLike" style="background-color:white;">
                        <div class="post-details-container">
                            <div class="post-details-inner">
                                <div class="post-user_img">
                                    <img src=${e.target.parentElement.parentElement.querySelector(".card_autor_img").src}></img>
                                </div>
                                <div class="post_info">
                                    <a href="#"><span class="post_info_username">${footer.querySelector(".card_autor_name").textContent}</span></a>
                                    <div class="post_info_hours">${footer.querySelector(".card_footer_fecha").firstElementChild.textContent}</div>
                                </div>
                            </div>
                            <div class="container-count-details">
                                <span clas="count-likes">
                                    <svg viewBox="0 0 17 15.736" class="svg-like" xmlns="http://www.w3.org/2000/svg" id="svg-like"> 
                                            <path d="M0,40.045H2.909V31.318H0Zm16-8a1.459,1.459,0,0,0-1.455-1.455H9.964l.727-3.345v-.218a1.525,1.525,0,0,0-.291-.8L9.6,25.5,4.8,30.3a1.22,1.22,0,0,0-.436,1.018v7.273a1.459,1.459,0,0,0,1.455,1.455h6.545a1.437,1.437,0,0,0,1.309-.873l2.182-5.164a1.238,1.238,0,0,0,.073-.509V32.045H16Z" transform="translate(0.5 -24.81)"></path>
                                    </svg>
                                    ${elementP.nextElementSibling.firstElementChild.textContent}
                                </span> 
                                <span id="count-comments">
                                    0 comentarios
                                </span>
                            </div>
                            <h2 style="pading: 1rem 0;">Comentarios</h2>
                            <div class="container-users">
                                <!--<ul class="list-users">
                                    <div class='loader-container loader-inter' id="loaderLike"></div>
                                </ul>-->
                                <!-- Contenedor Principal -->
                                <!-- Contenedor Principal -->
                                <div class="comments-container">
                                    <ul id="comments-list" class="comments-list">
                                    </ul>
                                </div>
                            </div>
                            <div class="comment-post">
                                <input type="text" placeholder="comenta algo">
                                <i class="fas fa-chevron-right" id="send-comment"></i>
                            </div>    
                        </div>
                    </div>
                `;

                    //Instanciamos un objeto de tipo modal, y le pasamos el container
                    //el cuál contendrá toda nuesto contenido de las imagenes y los usuarios
                    //que dieron like a esa determinada publicación
                    let modalm = new Modal(null, container_img, null, true, true);
                    modalm.open();
                    modalm.container.firstElementChild.style.maxWidth = "100%";
                    modalm.container.firstElementChild.style.padding = "0";
                    modalm.container.classList.add("container-details");

                    //para poder ver los usuarios que dieron like a la publicación
                    viewUserLike(idPost);

                    /*modalm.container.firstElementChild.style.margin = "1rem 2rem";*/
                    document.body.classList.add("scrollOff");

                    sendComment(document.getElementById("send-comment"),document.getElementById("send-comment").previousElementSibling,
                    e.target.nextElementSibling.lastElementChild.textContent,
                    document.getElementById("count-comments"),
                    document.getElementById("comments-list"));//cuando quiera publicar

                    /*let loader = document.getElementById("loaderLike");
                     loader.classList.add("loader-active");*/
                    let dat = new FormData();
                    dat.append("accion", "detallePost");
                    dat.append("id", idPost);
                    let request = {
                        method: 'Post',
                        url: 'post',
                        multipart: true,
                        data: dat
                    };
                    
                    let loaderComments = mostrarLoaderCommentario();
                    
                    AJAX.ajax(request).then((response) => {
                        return removeLoader(loaderComments,response);
                    }).then((response) => {//luego de realizar la petición y pintar quitamos el loader
                        let json = JSON.parse(response.responseText);
                        console.log(json);
                        printComments(json);
                    }).catch((error) => {
                        /*loader.classList.remove("loader-active");
                         console.log("error: " + error);*/
                    });
                }
            });
        }
        
        const mostrarLoaderCommentario  = () => {
            let parent = document.getElementById("comments-list");
          
            let loader = document.createElement("div");
            loader.classList.add("loader-container", "loader-active", "loader-especifico");
            parent.appendChild(loader);
            
            return {"loader": loader, "parent": parent};
        };

        const printComments = (json) => {
            if (json.estado) {
                //agregamos la cantidad de comentarios...
                document.getElementById("count-comments").textContent = `${json.allComments.length} comentarios`;

                //agregamos los comentarios
                let users = json.allComments;
                let container = document.getElementById("comments-list");
                let fragment = document.createDocumentFragment();
                users.forEach(us => {
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
                                <img class="" src=${us.user.url} alt=${us.user.username}">
                            </div>
                                                    
                                                    
                            <!-- Contenedor del Comentario -->
                            <div class="comment-box">
                                <div class="comment-head">
                                    <h6 class="comment-name by-author"><a href=perfil?id=${us.user.idPerson}>${us.user.username}</a></h6>
                                        <span>${us.fecha}</span>
                                        <i class="fa fa-heart"></i>
                                </div>
                                <div class="comment-content">
                                    ${us.texto}
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
                    fragment.appendChild(item);
                });
                container.appendChild(fragment);
            }
        };

    };

    openModalImg("publicaciones");

})(document, window);
