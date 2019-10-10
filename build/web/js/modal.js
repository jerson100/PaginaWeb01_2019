

const openModalAbstract = (elementId, header, body, footer, callback) => {

    let element = document.getElementById(elementId);

    let modal;

    if (element) {
        element.addEventListener('click', e => {
            //llenamos el modal
            modal = createModal("modal-abstract",true);

            modal.querySelector(".header").appendChild(header);

            modal.querySelector(".body").appendChild(body);

            if (footer) {
                modal.querySelector(".footer").appendChild(footer);
            }

            document.body.appendChild(modal);
            document.body.classList.add("scrollOff");
            callback();//ejecutamos el callback
        });

    }

};

const createModalMensaje = (header=null, body, footer=null, callback=()=>{}) => {
    //llenamos el modal
    let modal = createModal("modal-abstract",false);

    if (header) {
        modal.querySelector(".header").appendChild(header);
    }

    modal.querySelector(".body").appendChild(body);

    if (footer) {
        modal.querySelector(".footer").appendChild(footer);
    }

    document.body.appendChild(modal);
    document.body.classList.add("scrollOff");
    callback();//ejecutamos el callback
    return modal;
}

/*
 Nos devuelve un modal, el cuál debemos llenar en su contenido
 */
const createModal = (type, closable) => {
    //create modal
    let modalContainer = document.createElement("div");
    modalContainer.classList.add("modal-container");
    modalContainer.innerHTML = `
        <div class="modal-content">
            <div class="modal-inner${type != null ? ' ' + type : ''}">
                <div class="header"></div>
                <div class="body"></div>
                <div class="footer"></div>
            </div>
        </div>
        <div class="close-modal">x</div>
    `;
    if (closable) {

        let close = modalContainer.querySelector(".close-modal");

        closeModal(close, modalContainer);
    }

    return modalContainer;
}

const closeModal = (close, modal) => {
    close.addEventListener("click", j => {
        document.body.removeChild(modal);
        document.body.classList.remove("scrollOff");
    });
};




