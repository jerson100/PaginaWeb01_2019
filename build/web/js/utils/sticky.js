const sticky = (idElement, topViewport,top) => {
    
    let element = document.getElementById(idElement);
    let client = element.getBoundingClientRect();
    let topE = topViewport - top;//top inicial
    let width = client.width;
    let mediaQuery = window.matchMedia("(min-width: 1024px)");
    
    document.addEventListener('scroll', e => {

        if (mediaQuery.matches && scrollY >= topE) {

            element.style.position = "fixed";
            element.style.top = `${top}px`;
            element.style.width = width + "px";

        } else {
            
            element.style = "";
            
        }

    });
    
};
