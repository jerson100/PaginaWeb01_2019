class AJAX {
    static ajax(request) {
        return new Promise((resolve, reject) => {
            let xhr;
            if (window.XMLHttpRequest) {
                xhr = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xhr.open(request.method, request.url, true);
            if(!request.multipart){
               xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            }
            xhr.addEventListener('load', response => {
                resolve(response.target); //resuelve la promesa
            });
            xhr.send(request.data);
        });
    }
}

AJAX.READY_STATE_UNINITIALIZED = 0;
AJAX.READY_STATE_LOADING = 1;
AJAX.READY_STATE_LOADED = 2;
AJAX.READY_STATE_INTERACTIVE = 3;
AJAX.READY_STATE_COMPLETE = 4;

/*
const ajax = (request) => {
    return new Promise((resolve, reject) => {
        let xhr = new XMLHttpRequest();
        xhr.open(request.method, request.url, true);

        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhr.addEventListener('load', response => {
            resolve(response.target);//resuelve la promesa
        });

        xhr.send(request.data);
    });
};*/

