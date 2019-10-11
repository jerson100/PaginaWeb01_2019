createCustomElement = (type, attributes, childreen) => {
    let element = document.createElement(type);
    if (childreen) {
        childreen.forEach(child => {
            if (child.nodeType) {//elementos html y document fragment
                if (child.nodeType === 1 || child.nodeType === 11) {
                    element.appendChild(child);
                }
            } else {//string
                element.innerHTML += child;
            }
        });
    }
    addAttributes(element, attributes);
    return element;
};

createCustomElementClass = (type, allclass, childreen) => {
    let element = document.createElement(type);
    if (childreen) {
        childreen.forEach(child => {
            if (child.nodeType) {//elementos html y document fragment
                if (child.nodeType === 1 || child.nodeType === 11) {
                    element.appendChild(child);
                }
            } else {//string
                element.innerHTML += child;
            }
        });
    }
    addAttributesClass(element,allclass);
    return element;
}

//le pasamos un parámetro rest
const addAttributesClass = (element, ...attrClassArray) => {
    if (element) {
        attrClassArray.forEach(atrr => {
            element.classList.add(atrr);
        });
    }
};

const addAttributes = (element, attrObj) => {
    for (let attr in attrObj) {
        //si ese objeto tiene en si mismo ese atributo
        if (attrObj.hasOwnProperty(attr)) {
            element.setAttribute(attr, attrObj[attr]);
        }
    }
};