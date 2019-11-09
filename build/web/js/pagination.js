/* global AJAX */

'use-strict';

class Pagination {
    count_page = 0;
    countXpage = 0;
    current_page = -1;
    first_page = 1;
    last_page = 0;
    next = 0;
    prev = 0;
    data = [];

    set count_page(count_page) {
        this.count_page = count_page;
    }
    ;
            set countXpage(countXpage) {
        return this.countXpage = countXpage;
    }
    ;
            set current_page(current_page) {
        return this.current_page = current_page;
    }
    ;
            set first_page(first_page) {
        return this.first_page = first_page;
    }
    ;
            set last_page(last_page) {
        return this.last_page = last_page;
    }
    ;
            set next(next) {
        return this.next = next;
    }
    ;
            set prev(prev) {
        return this.prev = prev;
    }
    ;
            set data(data) {
        return this.data = data;
    }
    ;
            get count_page() {
        return this.count_page;
    }
    ;
            get countXpage() {
        return this.countXpage;
    }
    ;
            get current_page() {
        return this.current_page;
    }
    ;
            get first_page() {
        return this.first_page;
    }
    ;
            get last_page() {
        return this.last_page;
    }
    ;
            get next() {
        return this.next;
    }
    ;
            get prev() {
        return this.prev;
    }
    ;
            get data() {
        return this.data;
    }
    ;
}


const pagination = (containerId,idContainerPost) => {
    
    let container = document.getElementById(containerId);

    if (container) {

        container.addEventListener('click', function (e) {

            /*
             *  Si el elemento html no es un botón, entonces
             *  No deberíamos de realizar nada
             */
            
            if (e.target.tagName === 'BUTTON') {

                /*
                 Si el botón pulsado corresponse a la paginación actual,
                 entonces no debemos realizar la petición ajax.
                */
               
                if (!e.target.classList.contains("current-page")) {
                    
                    let paginacion = getPagination(e.target);
                    
                    if(paginacion!==null){
                        
                        //creamos el loader
                        /*let loader = createLoader();*/
                        
                        //creamos el loader - style facebook
                        createLoaderFacebook(idContainerPost);
                        
                        //realizamos la petición ajax

                        let data = new FormData();

                        data.append("current-page", JSON.stringify(paginacion));
                        data.append("accion", "allPostXpagination");

                        let request = {
                            method: 'POST',
                            url: 'post',
                            multipart: true,
                            data: data
                        };
                        
                        AJAX.ajax(request)
                                .then((response) => {//respuesta ajax
                                    return drawCards(JSON.parse(response.responseText),idContainerPost,container,e.target); 
                                })
                                .then(()=>{
                                    console.log("Terminó de listar");
                                    //removemos el loader.
                                   /*document.body.removeChild(loader);*/
                                })
                                .catch(response=>{
                                    //removemos el loader si ocurre una excepción.
                                   /*document.body.removeChild(loader);*/
                                });
                        
                    }

                }

            }

        });

    }

};

/*
 * 
 * @param {Integer} idContainerPost
 * @returns {Loader - div}
 * 
 */

const createLoaderFacebook = (idContainerPost) => {
  
    let container = document.getElementById(idContainerPost);
    
    let div = document.createElement("div");
        
        div.innerHTML = `
            <div class="animate-progrees title"></div>
            <div class="animate-progrees imagen"></div>
            <div class="container-footer">
                <div class="info-autor">
                    <div class="animate-progrees img-autor"></div>
                    <div class="animate-progrees username"></div>
                </div>
                <div>
                    <div class="animate-progrees fecha"></div>
                </div>
            </div>
        `;
    
    Array.from(container.children).forEach(element=>{
        
        let divClonado = div.cloneNode(true);
        
        divClonado.className = element.classList.value;
        divClonado.style.border = "none";
        divClonado.classList.add("card-animation");
        
        container.removeChild(element);
        container.appendChild(divClonado);
        
    });
    
};

const getPagination = (current_page) => {
    
    let currentPage = 'not';
    
    switch (current_page.textContent) {
        case '<':
            currentPage = current_page.id;
            break;
        case '>':
            currentPage = current_page.id;
            break;
        default:
            currentPage = current_page.textContent;
    }

    if(validateNumber(currentPage)){
        let objPagination = new Pagination();
        objPagination.current_page = currentPage;
        objPagination.next = parseInt(currentPage) + 1;
        objPagination.prev = parseInt(currentPage) - 1;
        return objPagination;
    }

    return null;
    
};


//pintamos las publicaciones en la pantalla...
const drawCards = (json,idContainerPost,containerNav,elementPressed) =>{
  
    return new Promise((resolve,reject)=>{
       
        if(json===null || json === undefined){
            
            reject("argumento pasado no es válido");
            
        }else{
            
            let containerPost = document.getElementById(idContainerPost);
            
            if(containerPost){
                
                containerPost.innerHTML = '';
                
                clearSelection(containerNav);
                
                elementPressed.classList.add("current-page");
                
                //pintamos en el containerPpost
                
                let timeSeconds = 0; // Tiempo de retardo para que se imprima cada post.
                let count = 0; // lleva la cuenta de las publicaciones imprimidas en el DOM
                
                //resolvemos la promesa si no se encuentran publicaciones.
                
                if(json.data.length === 0) resolve();
                
                json.data.forEach(post=>{
                    
                    setTimeout(()=>{
                        
                        count++;
                        
                        let item = printPost(containerPost,post);
                        
                        if(count === json.data.length){
                            
                            if(count%2!==0){
                                 
                                item.style.marginRight = "0";
                                 
                            }
                            
                            //resolvemos la promesa cuando se termine de imprimir el último post.
                            
                            resolve();
                            
                        }
                        
                    },timeSeconds);
                    
                    timeSeconds += 400;
                    
                });
                
            }else{
                
                reject("El id del contenedor de los post no existe.");
                
            }
            
        }
        
    });
    
};

const printPost = (container,post) =>{
    
    let itemPost = document.createElement("div");
                    
    itemPost.classList.add("je-item","article-post", "fade");
                    
    itemPost.innerHTML = `
        <article class="card" id="post-${post.idPost}">
           <header class="card-header">
                <a href="post?id=${post.idPost}">
                    <h3 class="card-header_title">${post.title}</h3>
                    <div class="card-header_img">
                        <img src="${post.urlImage}" alt="${post.title}" class="img-post">
                    </div>
                </a>
            </header>
            <footer class="card-footer">
                <div class="card_autor" id="user-${post.user.idPerson}">
                    <img src="${post.user.url}" alt="${post.user.username}" class="card_autor__img img-user">
                    <a href="perfil?id=${post.user.idPerson}" class="card_autor__name">${post.user.username}</a>
                </div>
                <div class="card_fecha">
                    <span>${post.dateFormat}</span>
                </div> 
            </footer>
        </article>
    `;
                                
    container.appendChild(itemPost); //agregamos el post al contenedor de post
    
    return itemPost;
    
};

const createLoader = () =>{
    let loader = document.createElement("div");
    loader.classList.add("loader-container", "loader-active");
    document.body.appendChild(loader);
    return loader;
};

const clearSelection = (containerNav)=>{
    
    //eliminamos el currentPage de los botones
    
    let elementPrev = containerNav.querySelector(".current-page");
    
    if(elementPrev){
        
        elementPrev.classList.remove("current-page");
    
    }
        
};
