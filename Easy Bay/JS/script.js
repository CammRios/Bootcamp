function alerta(elemento){
    alert("Your cart is empty!")
}
function cambia_imagen(imagen_original){
    imagen_original.src="images/assets/succulents-2.jpg";
}

function regresa_imagen(imagen_cambiada){
    imagen_cambiada.src="images/assets/succulents-1.jpg"
}

function delete_cookies(){
    var cookie = document.querySelector
    ('.cookie')
    cookie.remove();
}