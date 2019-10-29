/* global AJAX, openModalError */

'use strict';

class Modal {

    constructor(header, body, footer, closable = false, isActive = false) {
        this.body = body;
        this.footer = footer;
        this.header = header;
        this.isActive = isActive;
        this.container = this.create(header, body, footer, closable);
        if (isActive) {
            this.container.style.display = "none";
            document.body.appendChild(this.container);
    }
    }

    //crea el modal
    create(header, body, footer, closable) {
        let modalContainer = document.createElement("div");
        let type = null;
        modalContainer.classList.add("modal-container");
        modalContainer.innerHTML = `
            <div class="modal-content">
                <div class="modal-inner${type !== null ? ' ' + type : ''}">
                </div>
            </div>
          `;
        let modalInner = modalContainer.querySelector('.modal-inner');
        if (header !== null)
            modalInner.append(header);
        if (body !== null)
            modalInner.append(body);
        if (footer !== null)
            modalInner.append(footer);
        if (closable) {
            let clos = document.createElement("div");
            clos.innerHTML = `
              <div class="close-modal">x</div>
            `;
            modalContainer.appendChild(clos);
            clos.addEventListener('click', e => {
                if (this instanceof ModalMensaje) {
                    this.close("msg-normal-close-mensaje");
                } else {
                    this.close();
                }
            });
        }
        return modalContainer;
    }

    open(classAnimate = "msg-normal-open") {
        return new Promise((resolve, reject) => {
            if (this.container) {
                if (classAnimate !== '') {
                    this.container.firstElementChild.classList.add(classAnimate);
                }
                if (this.isActive) {
                    this.container.style.display = "flex";
                } else {
                    document.body.appendChild(this.container);
                }
            }
            resolve();
        });

    }

    close(classAnimate = "msg-normal-close") {
        return new Promise((resolve, reject) => {
            if (this.container) {
                if (classAnimate !== '') {
                    this.container.firstElementChild.classList.add(classAnimate);
                }
                setTimeout(() => {
                    if (this.isActive) {
                        this.container.firstElementChild.classList.remove("msg-normal-open");
                        this.container.firstElementChild.classList.remove("msg-normal-close");
                        this.container.style.display = "none";
                        document.body.classList.remove("scrollOff");//por si tiene el scroll desactivado
                    } else {
                        document.body.removeChild(this.container);
                    }
                    resolve();
                }, (this instanceof ModalMensaje) ? 500 : 1000);
            } else {
                resolve();
            }
        });
    }

}

class ModalMensaje extends Modal {
    constructor(mensaje, closable = false) {
        let body = document.createElement("p");
        body.textContent = mensaje;
        super(null, body, null, closable, false);
        this.mensaje = mensaje;
        this.container.classList.add("modal-msg");
        this.container.lastElementChild.firstElementChild.classList.add("close-modal-msg");
        this.container.firstElementChild.classList.remove("modal-content");
        this.container.classList.remove("modal-container");
    }
}

class ModalMensajeError extends ModalMensaje {
    constructor(mensaje, closable = false) {
        super(mensaje, closable);
        this.container.classList.add("msg-error");
    }
}

class ModalMensajeSuccess extends ModalMensaje {
    constructor(mensaje, closable = false) {
        super(mensaje, closable);
        this.container.classList.add("msg-success");
    }
}

const openModalImg = (p) => {

    let padre = document.getElementById(p);

    if (padre) {
        padre.addEventListener('click', e => {
            let elementP = e.target;//elemento pulsado
            if (elementP.tagName === 'IMG') {
                if(document.querySelector(".container-details")!==null){
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
                                    <svg viewBox="0 0 17 15.736" class="svg-like" xmlns="http://www.w3.org/2000/svg"> 
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

                let lista_users = container_img.querySelector(".list-users");

                //Instanciamos un objeto de tipo modal, y le pasamos el container
                //el cuál contendrá toda nuesto contenido de las imagenes y los usuarios
                //que dieron like a esa determinada publicación
                let modalm = new Modal(null, container_img, null, true, true);
                modalm.open();
                modalm.container.firstElementChild.style.maxWidth = "100%";
                modalm.container.firstElementChild.style.padding = "0";
                modalm.container.classList.add("container-details");
                
                /*modalm.container.firstElementChild.style.margin = "1rem 2rem";*/
                document.body.classList.add("scrollOff");

                sendComment(document.getElementById("send-comment"), e.target.nextElementSibling.lastElementChild.textContent);//cuando quiera publicar

                /*let loader = document.getElementById("loaderLike");
                 loader.classList.add("loader-active");*/
                let dat = new FormData();
                dat.append("accion", "detallePost");
                dat.append("id", e.target.nextElementSibling.lastElementChild.textContent);
                let request = {
                    method: 'Post',
                    url: 'post',
                    multipart: true,
                    data: dat
                };
                AJAX.ajax(request).then((response) => {
                    let json = JSON.parse(response.responseText);
                    console.log(json);
                    printComments(json);
                    /*return printUsersLikes(json.usuarios, lista_users);*/
                }).then(() => {//luego de realizar la petición y pintar quitamos el loader
                    /*loader.classList.remove("loader-active");*/
                }).catch((error) => {
                    /*loader.classList.remove("loader-active");
                     console.log("error: " + error);*/
                });
            }
        });
    }

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
                
                let item = createCustomElement("li",{"class":"list-level"},[inner]);
                fragment.appendChild(item);
            });
            container.appendChild(fragment);
        }
    };

    const printUsersLikes = (users, listContainer) => {
        return new Promise((resolve, reject) => {
            if (users === null ||
                    users === undefined ||
                    listContainer === null ||
                    listContainer === undefined) {
                reject("Error....");
            } else {
                let fragment = document.createDocumentFragment();
                console.log(users);
                users.forEach(e => {
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
                listContainer.appendChild(fragment);
                resolve();
            }
        });
    };

    const sendComment = (send, idPost) => {
        send.addEventListener('click', e => {
            let parent = send.previousElementSibling;//hermano anterior(input)
            let texto = parent.value;
            let dat = new FormData();
            dat.append("accion", "comment");
            dat.append("id", idPost);
            dat.append("texto", texto);
            let request = {
                method: 'Post',
                url: 'post',
                multipart: true,
                data: dat
            };
            AJAX.ajax(request)
                .then((response) => openModalError(JSON.parse(response.responseText)))
                .then((response)=>{
                    document.body.removeChild(response.container);
                    location.href = response.data.url;
                })
                .catch((dat)=>{//cuando url es vacío
                    console.log(dat);
                    printComment(dat.data,texto);
                });
        })
    };
    
    
    const openModalError = (json)=>{
        return new Promise((resolve,reject)=>{
            if(json===null || 
                json === undefined ||
                json.mensaje === null ||
                json.url === null ||
                json.url === ''){
                reject({"data":json,"msg":"Error"});
            }else{
                let modalM = new ModalMensajeError(json.mensaje,true);
                modalM.open("msg-normal-open-text");//abrimos el modal
                setTimeout(()=>{
                    resolve({"container":modalM.container,"data":json});
                },2000);
            }
        });
    };
    
    const printComment = (json,texto)=>{
        
        if(json.estado){
            
            //actualizamos la cantidad de comentarios + 1
            let container_count = document.getElementById("count-comments");
            let array = container_count.textContent.split(/[ ]+/);
            console.log(array);
            container_count.textContent = parseInt(array[0]) + 1 + " " + array[1];
            
            let us = json.user;
            let container = document.getElementById("comments-list");
            let fragment = document.createDocumentFragment();
            let inner = `
                    <div class="comment-main-level">
                            <!-- Avatar -->
                            <div class="card-user">
                                                    
                                                    <div class="card-user-type"><svg viewBox="0 0 16 16" class="svg-icon-admin" xmlns="http://www.w3.org/2000/svg"><g transform="translate(-97.103 -44.137)">
                                                        <path d="M113.1,52.137a8,8,0,1,0-8,8,8,8,0,0,0,8-8" fill="#f0c419"></path>
                                                        <path d="M155.4,88.276l1.7,3.434,3.63.566-2.578,2.673.8,3.549L155.4,96.951,151.847,98.5l.8-3.549-2.578-2.673,3.63-.566Z" transform="translate(-50.299 -41.917)" fill="#ffe69f"></path></g>
                                                        </svg>
                                                        <span class="user-type_name">adm</span>
                                                    </div>
                                                    
                                                    <img class="card_autor_img comment-avatar" src=${us.url} alt=${us.username}">
                            </div>
                            <!--<div class="comment-avatar"><img src=${us.url} alt=${us.username}></div>-->
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
                
                let item = createCustomElement("li",{"class":"list-level"},[inner]);
                item.classList.add("opacityBorderActive");
                fragment.appendChild(item);
            
                container.appendChild(fragment);
                let parent = container.parentElement.parentElement;
                parent.scrollTo(0,parent.scrollHeight);
                setInterval(()=>{
                    item.classList.remove("opacityBorderActive");
                },3000);
            
        }
        
    }

};
