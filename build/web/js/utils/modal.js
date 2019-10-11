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
                //creamos el modal
                let container = document.createElement("div");
                container.classList.add("modal-imagen");
                container.style.display = "flex";

                let cnImg = document.createElement("div"),
                        im = document.createElement("img");
                cnImg.classList.add("containerImg");
                im.classList.add("modal-img");
                cnImg.appendChild(im);

                let cnM = document.createElement("div");
                cnM.classList.add("containerLike");
                cnM.style.backgorundColor = "White";
                
                let titlee = document.createElement("h3");
                titlee.textContent = 'Usuarios que dieron like';

                cnM.appendChild(titlee);

                let container2 = document.createElement("div");
                container2.classList.add("container-user");
                let ul = document.createElement("ul");
                ul.setAttribute("class", "list-users");
                container2.appendChild(ul);
                cnM.appendChild(container2);

                im.src = elementP.src;
                im.alt = elementP.alt;

                container.appendChild(cnImg);
                container.appendChild(cnM);

                //creamos el modal
                let modalm = new Modal(null, container, null, true, false);
                modalm.open();
                modalm.container.firstElementChild.style.maxWidth = "100%";
                modalm.container.firstElementChild.style.padding = "0";
                document.body.classList.add("scrollOff");

                //realizamos la petición ajax para ver los usuarios que dieron clicka  esa publicación
                let title = e.target.previousElementSibling.firstElementChild.textContent;
                let dat = new FormData();
                dat.append("accion", "listarlikes");
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
                    printUsersLikes(json.usuarios,ul);
                }).catch((reject) => {
                    console.log(reject);
                });

            }
        });
    }

    const printUsersLikes = (users,listContainer) => {
        let fragment = document.createDocumentFragment();
        console.log(users);
        users.forEach(e => {
            let li = document.createElement("li");
            li.setAttribute("class","user-item");
            li.innerHTML = `
                    <div class="flex">
                        <img class="user-img-like" src=${e.url} alt=${e.username}></img>
                        <span class="us">${e.username}</span>
                    </div>
            `;
            fragment.appendChild(li);
        });
        
        listContainer.appendChild(fragment);        
    };

};
