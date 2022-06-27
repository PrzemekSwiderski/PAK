const switchRegister = document.getElementById("switch-register")
const switchAuth = document.getElementById("switch-auth")
const formRegister = document.getElementById("form-register")
const formAuth = document.getElementById("form-auth")

if (switchRegister != null && formRegister !=null){
    switchRegister.onclick = function (){
        formRegister.hidden = false;
        formAuth.hidden = true;
    }
}

if (switchAuth != null && formAuth !=null){
    switchAuth.onclick = function (){
        formRegister.hidden = true;
        formAuth.hidden = false;
    }
}