const openModalImg = (p) => {

    let padre = document.getElementById(p);

    if(padre){
        padre.addEventListener('click',e=>{
            let elementP = e.target;//elemento pulsado
            if(elementP.tagName === 'IMG'){
                //creamos el modal
                let modal = createModal(null);
                
                //llenamos el modal
                modal.querySelector(".header").innerHTML = `
                    <p class="modal-title t2">${elementP.alt}</p>
                `;

                modal.querySelector(".body").innerHTML = `
                    <img class="modal-img" src=${elementP.src} alt=${elementP.alt}>
                `;

                modal.querySelector(".footer").innerHTML = `
                    <p class="txt-footer">${elementP.width} * ${elementP.height}</p>
                `;

                document.body.appendChild(modal);
                document.body.classList.add("scrollOff");

            }
        });
    }

};

const openModalAbstract = (elementId,header,body,footer)=>{

    let element = document.getElementById(elementId);

    let modal;

    element.addEventListener('click',e=>{
        //llenamos el modal
        modal = createModal("modal-abstract");

        modal.querySelector(".header").appendChild(header);

        modal.querySelector(".body").appendChild(body);

        if(footer){
            modal.querySelector(".footer").appendChild(footer);  
        }
            
        document.body.appendChild(modal);
        document.body.classList.add("scrollOff");
    });

};

/*
    Nos devuelve un modal, el cuál debemos llenar en su contenido
*/
const createModal = (type)=>{
    //create modal
    let modalContainer = document.createElement("div");
    modalContainer.classList.add("modal-container");
    modalContainer.innerHTML = `
        <div class="modal-content">
            <div class="modal-inner${type!=null?' '+type:''}">
                <div class="header"></div>
                <div class="body"></div>
                <div class="footer"></div>
            </div>
        </div>
        <div class="close-modal">x</div>
    `;

    let close = modalContainer.querySelector(".close-modal");    

    closeModal(close,modalContainer);

    return modalContainer;
}

const closeModal = (close,modal) => {
    close.addEventListener("click",j=>{
        document.body.removeChild(modal);
        document.body.classList.remove("scrollOff");
    });
};




