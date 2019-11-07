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
                console.log(e.target);
                if (!e.target.classList.contains(".current-page")) {
                    
                    let paginacion = getPagination(e.target);
                    
                    if(paginacion!==null){
                        
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
                                .then((response) => {
                                    //console.log(JSON.parse(response.responseText));
                                    return drawCards(JSON.parse(response.responseText),idContainerPost,container,e.target); 
                                })
                                .then()
                                .catch(response=>{
                                   console.log(response); 
                                });
                        
                    }

                }

            }

        });

    }

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
                
                json.data.forEach(post=>{
                   
                    let itemPost = document.createElement("div");
                    
                    itemPost.classList.add("je-item","article-post");
                    
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
                                        <span>Hace 31 días</span>
                                    </div> 
                            </footer>
                        </article>
                    `;
                                
                    containerPost.appendChild(itemPost); //agregamos el post al contenedor de post     
                                
                });
                
                resolve();
                
            }else{
                reject("El id del contenedor de los post no existe.");
            }
            
        }
        
    });
    
};

const clearSelection = (containerNav)=>{
    
    //eliminamos el currentPage de los botones
    
    let elementPrev = containerNav.querySelector(".current-page");
    
    if(elementPrev){
        
        elementPrev.classList.remove("current-page");
    
    }
        
};
