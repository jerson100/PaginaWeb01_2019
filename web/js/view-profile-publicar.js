
//abstract modal

const header = document.createElement("div");
const body = document.createElement("div");
const footer = null;

header.innerHTML = `
              <p style="padding: 1rem 0;text-align:center;">Publicar IMG</p>
          `;

body.innerHTML = `
              <form id="frmPost">
                  <div class="form-item">
                      <label for="titulo">Título:</label>
                      <input type="text" id="titulo" name="titulo">
                  </div>
                  <div class="form-item">
                      <label for="img">IMG:</label>
                      <input type="file" id="img" name="img">
                  </div>
                  <div class="form-item">
                      <input class="button center-button" type="submit" value="Publicar">
                  </div>
              </form>
          `;

let modal = new Modal(header, body, null, true, true);

let post = document.getElementById("publicar");
if (post) {
    post.addEventListener('click', e => {
        modal.open();
        document.body.classList.add("scrollOff");
    });
}

const frmPost = document.getElementById("frmPost");
frmPost.addEventListener("submit", evt => {
    evt.preventDefault();
    loader.classList.add("loader-active");
    let dat = new FormData();
    dat.append("accion", "publicar");
    dat.append("titulo", titulo.value);
    dat.append("img", img.files[0]);
    let request = {
        method: 'POST',
        url: 'perfil',
        data: dat,
        multipart: true
    };
    AJAX.ajax(request)
            .then((response) => {
                switch (response.status) {
                    case 200:
                        draw(JSON.parse(response.responseText));
                        break;
                    case 400:
                        break;
                }
loader.classList.remove("loader-active");
            });
});

const draw = (json) => {
    let msgError = document.body.querySelector(".modal-msg");
    console.log(msgError);
    console.log(json.estado);
    console.log(json.mensaje);
    console.log(json.url);
    let modal;
    if (msgError !== null)document.body.removeChild(msgError);
    if (json.estado) {
        modal = new ModalMensajeSuccess(json.mensaje, true);
        setTimeout(() => {
            location.href = json.url;
        }, 500);
    } else {
        modal = new ModalMensajeError(json.mensaje, true);
    }
    modal.open("msg-normal-open-text");
}





