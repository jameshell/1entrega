function yesnoCheck() {
    if (document.getElementById('yesCheck').checked) {
        document.getElementById('ifYes').style.visibility = 'visible';
    }
    else document.getElementById('ifYes').style.visibility = 'hidden';
    
    if (document.getElementById('noCheck').checked) {
        document.getElementById('ifNo').style.visibility = 'visible';
    }
    else document.getElementById('ifNo').style.visibility = 'hidden';


}

function crud() {
    if (document.getElementById('borrar').checked) {
        document.getElementById('borrado').style.visibility = 'visible';
    }
    else document.getElementById('borrado').style.visibility = 'hidden';
    
    if (document.getElementById('buscar').checked) {
        document.getElementById('buscado').style.visibility = 'visible';
    }
    else document.getElementById('buscado').style.visibility = 'hidden';



}