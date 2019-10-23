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
                console.log(response.target.status);
                if(xhr.readyState === 4){
                    if(response.target.status===200){
                        resolve(response.target)
                    }else{
                        reject(response.target);
                    }
                }
            });
            xhr.send(request.data);
        });
    }
}

/*
 * Estados del objeto XMLHttpRequest
AJAX.READY_STATE_UNINITIALIZED = 0;
AJAX.READY_STATE_LOADING = 1;
AJAX.READY_STATE_LOADED = 2;
AJAX.READY_STATE_INTERACTIVE = 3;
AJAX.READY_STATE_COMPLETE = 4;
 **/