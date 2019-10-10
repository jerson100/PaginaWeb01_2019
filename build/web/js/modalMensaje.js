const openMensajeModal = (container,timeIni,timeFin)=>{
    return new Promise((resolve,reject)=>{
        iniMsgError(container,timeIni)
            .then((container)=>{
                    return finMsgError(container,timeFin);
               })
            .then(()=>{
                resolve();
            });
    });
}

const openMensajeModal2 = (container,timeIni)=>{
    return new Promise((resolve,reject)=>{
        iniMsgError(container,timeIni)
            .then(()=>{
                resolve();
            });
    });
}

const iniMsgError = (container,timeIni)=>{
    return new Promise((resolve,reject)=>{
        //console.log("mensaje activo");
        setTimeout(() => {
            //console.log("mensaje activo quitado");
            container.classList.remove("active");
            container.classList.add("msgError-desactive");
            resolve(container);
        },timeIni);            
    });
}

const finMsgError = (container,timeFin)=>{
    return new Promise((resolve,reject)=>{
        //console.log("mensaje retrocediendo");
        setTimeout(() => {
            //console.log("mensaje eliminado");
            document.body.removeChild(container);
            resolve();
        },timeFin);
    });
}
