<%-- 
    Document   : chat
    Created on : 14/10/2019, 10:36:47 PM
    Author     : Jerson
--%>

<%@page import="model.Profile"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User userA = (User)request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=divace-width,initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/base/base.css" rel="stylesheet" type="text/css"/>
        <link href="css/theme/colors.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/buttons.css" rel="stylesheet" type="text/css"/>
        <link href="css/theme/fonts.css" rel="stylesheet" type="text/css"/>
        <link href="css/theme/typography.css" rel="stylesheet" type="text/css"/>
        <link href="css/layout/layout.css" rel="stylesheet" type="text/css"/>
        <link href="css/base/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/base/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/loader.css" rel="stylesheet" type="text/css"/>
        <link href="css/pages/chat/chat.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/modal.css" rel="stylesheet" type="text/css"/>
        <title>Chat</title>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"></jsp:include>
        <audio id="audioActive" type="audio/mp3" src="audio/iphone-notificacion.mp3" style="display: none;"></audio>
        <audio id="audioMessage" type="audio/mp3" src="audio/windows-notificacion.mp3" style="display: none;"></audio> 
        <div class="center">
            <div class="contacts" id="contacts">
                <div class="header">
                    <i class="fas fa-bars fa-2x"></i>
                    <h2>Contactos</h2>
                    <i id="close" class="fas fa-times fa-bars fa-2x"></i>
                </div>
                <div id="contactosActivos"></div>
            </div>
            <div class="chat">
                <div class="contact bar">
                    <div class="pic">
                        <img id="user" class="img-user" alt="">
                    </div>
                    <div style="display:none;" id="user-id-send"></div>
                    <div class="name" id="user-chat"></div>
                    <i id="closeChat" class="fas fa-times fa-bars fa-2x"></i>
                    <!--<div class="seen">
                        Today at 12:56
                    </div>-->
                </div>
                <div class="messages" id="chat">
                    <div class="time">
                        Today at 11:41
                    </div>
                </div>
                <div class="input">
                    <i class="fas fa-camera"></i><i class="far fa-laugh-beam"></i>
                    <input placeholder="Type your message here!" type="text" id="message" />
                    <i id="sendMessage" class="fas fa-caret-right"></i>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"></jsp:include>
        <script src="js/utils/modal.js" type="text/javascript"></script>
        <script src="js/toogle.js" type="text/javascript"></script>
        <script src="js/utils/elements.js" type="text/javascript"></script>
        <script>
            let userSend = document.getElementById("user-id-send");
            let messageUser = document.getElementById("message");
            let userNameSend = document.getElementById("user-chat");
            let chatt = document.getElementById("chat");
            let userImageSend = document.getElementById("user");
            
            class Message{
                constructor(idMessage,idUsuario,idSala,messageTxt,date,action){
                    this.idMessage = idMessage;
                    this.idUsuario =idUsuario;
                    this.idSala = idSala;
                    this.messageTxt = messageTxt;
                    this.date = date;
                    this.action = action;
                };
            };
            
            class User{
                constructor(idPerson,username,pass,idTypeUser,idState,email,url){
                    this.idPerson=idPerson;
                    this.username=username;
                    this.pass = "";
                    this.idTypeUser=idTypeUser;
                    this.idState=idState;
                    this.email=email;
                    this.url=url;
                };
            };
            
            const userobj = new User(<%=userA.getIdPerson()%>,
                                            '<%=userA.getUsername()%>',
                                            '',
                                            <%=userA.getIdTypeUser()%>,
                                            <%=userA.getIdState()%>,
                                            '<%=userA.getEmail()%>',
                                            '<%=userA.getUrl()%>');
                                    
            toogleButton("close","contacts","close-active",);
            toogleButton("closeChat","contacts","close-active")
            
            contacts.addEventListener('click',e=>{
                let p = e.target;
                if(p.tagName === 'IMG'){
                    chatt.innerHTML = '';
                    let closeI = document.getElementById("contacts");
                    console.log(closeI);
                    closeI.classList.add("close-active");
                    
                    let id = parseInt(p.parentElement.nextElementSibling.nextElementSibling.textContent);
                    let username = p.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.textContent;
                    userNameSend.textContent = username;
                    document.getElementById("user-id-send").textContent = id;
                    user.src = p.src;
                    user.alt = p.alt;
                }
            });
            
            sendMessage.addEventListener('click',e=>{
                let msg = messageUser.value;
                let id = parseInt(userSend.textContent);
                let message = new Message(-1,id,1,msg,new Date(), 'message');
                let data = {user : userobj,message : message};
                ws.send(JSON.stringify(data));                    
            });
            
            message.addEventListener("focus",e=>{
               let id = parseInt(userSend.textContent);
               let message = new Message(-1,id,1,'',new Date(), 'focus-writing');
               let data = {user : userobj,message : message};
               ws.send(JSON.stringify(data));
            });
            
            message.addEventListener("blur",e=>{
               let id = userSend.textContent;
               let message = new Message(-1,id,1,'',new Date(), 'blur-writing');
               let data = {user : userobj,message : message};
                ws.send(JSON.stringify(data));
            });
            
            let ws = new WebSocket("ws://" + document.location.host + "/ProyectoWeb01/chatsocket");
            
            ws.onopen = ()=>{
                const message = new Message(-1,<%=userA.getIdPerson()%>,1,'',new Date(), 'open');          
                let data = {user : userobj,message : message};
                ws.send(JSON.stringify(data));
            };
            
            ws.onclose = (e)=>{};
            
            ws.onerror = (e)=>{};
            
            ws.onmessage = (response)=>{
                let dat = JSON.parse(response.data);
                switch(dat.message.action){
                    case 'listar':
                        printAllUsers(dat.users);
                        break;
                    case 'message':
                        printMessage(dat.message,dat.user);
                        break;
                    case 'focus-writing':
                        focusWriting(dat.message,dat.user);
                        break;
                    case 'blur-writing':
                        blurWriting();
                        break;
                    case 'disconect':
                        mostrarModal(dat.message,dat.user);
                        printAllUsers(dat.users);
                        break;
                }
            };
            
            const blurWriting =()=>{
                let write = document.getElementById("writing");
                if(write)chat.removeChild(write);
            };
            
            const focusWriting = (dat,user)=>{
                let id = userSend.textContent;
                if(dat.idUsuario!=id && id == user.idPerson){
                    let container = document.createElement("div");
                    container.classList.add("message","stark");
                    container.setAttribute("id","writing");
                    container.innerHTML=`
                        <div class="typing typing-1"></div>
                        <div class="typing typing-2"></div>
                        <div class="typing typing-3"></div>
                    `;
                    chat.appendChild(container);
                }
            };
            
            const printMessage = (message,user)=>{
                console.log(userobj.idPerson +" - "+message.idUsuario);
                console.log(user.idPerson+" - "+userSend.textContent);
                //if(message.idUsuario===null || message.idUsuario===undefined)return;
                if( userobj.idPerson == message.idUsuario && user.idPerson != parseInt(userSend.textContent)){
                    chatt.innerHTML = '';
                    userNameSend.textContent = user.username;
                    userSend.textContent = user.idPerson;
                    userImageSend.src = user.url;
                }
                
                let colorC;
                if(user.idPerson==<%=userA.getIdPerson()%>){
                    colorC = "parker";
                }else{
                    colorC = "stark";      
                    document.getElementById("audioMessage").play();
                }
                let cont = document.createElement("div");
                cont.classList.add("message",colorC);
                cont.textContent = message.messageTxt;
                chat.appendChild(cont);
            };
            
            const printAllUsers = (users)=>{
                //document.getElementById("audioActive").play();
                let documentF = document.createDocumentFragment();
                users.forEach(us=>{
                    if(<%=userA.getIdPerson()%>!=us.idPerson){
                        let element = createCustomElement("div",{"class":"contact"},[]);
                        element.innerHTML = `
                            <div class="pic">
                                <img style="border-radius:50%;width:64px;height:64px;object-fit:cover;" src=`+us.url+`></img> 
                            </div>
                            <div class="badge">
                                14
                            </div>
                            <span style="display:none;">`+us.idPerson+`</span>
                            <div class="name">`+
                                us.username  
                                +`</div>
                            <!--<div class="message">
                                    That is America's ass 🇺🇸🍑
                            </div>-->
                        `;
                        documentF.appendChild(element);
                    }
                });
                contactosActivos.innerHTML = "";
                contactosActivos.appendChild(documentF);
            };
            
            
            const mostrarModal = (message,user)=>{
                let msgError = document.querySelector(".modal-msg");
                let modal;
                if (msgError !== null)document.body.removeChild(msgError);
                modal = new ModalMensajeSuccess(user.username+ " " + message.messageTxt, false);
                setTimeout(()=>{
                    document.body.removeChild(modal.container);
                },2000);
                modal.open("msg-normal-open-text");
            };
        </script>
        <script src="js/toogle.js"></script>
        <script>toogleButton('icon', 'navigation', 'active');</script>
        <script>toogleButton('user-arrow' , 'user-acces', 'active-profile-user');</script> 
        <script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
    </body>
</html>
