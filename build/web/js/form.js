//para los text-area
/*window.addEventListener('load', e => {*/

const initOyenteTeclado = () => {
    const txt = document.getElementById("description");
    const span = document.getElementById("count-desc");
    const max = 250;/*Longitud máxima de carácteres*/
    if(!txt){
        return;
    }
    
    txt.addEventListener('contextmenu', w => w.preventDefault());

    txt.addEventListener('keyup', evt => {
        let v = txt.value.length;
        if (evt.code === "KeyV")
            if (v > max)
                txt.value = txt
                        .value
                        .substring(0, max);
        updateSpan(false,span,txt,max);
    });

    txt.addEventListener('keypress', evt => {
        if (txt.value.length < max)
            updateSpan(true,span,txt,max);
        else
            evt.preventDefault();
    });
};

const updateSpan = (opc,span,txt,max) => {
    span.textContent = txt.value.length + (opc ? 1 : 0);
    if (txt.value.length >= max)
        span.classList.add("fulldata");
    else
        span.classList.remove("fulldata");
}

/*});*/