const grilla = document.getElementById("post-nav-grilla");
const column = document.getElementById("post-nav-column");
const group = document.querySelectorAll(".group-post");
const group_items = document.querySelectorAll(".container-card");

const images = document.querySelectorAll(".card-img-post");

//view grid
grilla.addEventListener('click',e=>{
   
    group.forEach(e=>{
        e.classList.add("gr");
        group_items.forEach(e2=>{
            e2.classList.add("group-item");
        });
        images.forEach(e3=>{
            e3.classList.add("grImg");
        });
    });

});

//view column
column.addEventListener('click',e=>{

    group.forEach(e=>{
        e.classList.remove("gr");
        group_items.forEach(e2=>{
            e2.classList.remove("group-item");
        });
        images.forEach(e3=>{
            e3.classList.remove("grImg");
        });
    });

});