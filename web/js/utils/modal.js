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
        let body = document.createElement("div");

        if (mensaje.nodeType) {//elementos html y document fragment
            if (mensaje.nodeType === 1 || mensaje.nodeType === 11) {
                body.appendChild(mensaje);
            }
        } else {//string
            body.innerHTML = mensaje;
        }

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


