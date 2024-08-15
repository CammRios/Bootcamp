function alerta(elemento){
    alert("Loading weather report of " + elemento.innerText)
    /*"texto"+var
    ´texto ${var}´*/
    
    var elemento_city= document.
    querySelector('#city');
    elemento_city.innerText=elemento.innerText;
}

function delete_cookies(){
    var cookie = document.querySelector
    ('.cookie')
    cookie.remove();
}
