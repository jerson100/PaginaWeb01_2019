const sticky = (idElement, top) => {

    let element = document.getElementById(idElement);
    let topE = element.offsetTop - top;
    let width = Array.from(element.getClientRects())[0].width;
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
