const toogleButton = (id1,id2,classStyle) => {
    let element1 = document.getElementById(id1),
        element2 = document.getElementById(id2);
    element1.addEventListener("click",e=>{
        if(element2.classList.contains(classStyle)){
            element2.classList.remove(classStyle);
        }else{
            element2.classList.add(classStyle);
        }
    });
};