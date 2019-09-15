//para los text-area
window.addEventListener('load', e => {
    const txt = document.getElementById("description");
    const span = document.getElementById("count-desc");
    const max = 250;/*Longitud máxima de carácteres*/
    
    txt.addEventListener('contextmenu',w=>w.preventDefault());
    
    txt.addEventListener('keyup', evt => {
        let v = txt.value.length;
        if(evt.code==="KeyV")
            if(v>max)
                txt.value = txt
                            .value
                            .substring(0,max);
        updateSpan(false);
    });
    
    txt.addEventListener('keypress', evt => {
        if (txt.value.length < max)updateSpan(true);
        else evt.preventDefault();
    });
    const updateSpan=(opc)=>span.textContent=txt.value.length+(opc?1:0);
});