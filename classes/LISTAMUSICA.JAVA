package Classes;
public class listaMusica {
    public music[] lista = new music[5];
    
public listaMusica() {
    lista[0] = new music("track" + 0, "artista" + 0, 40000);
    lista[1] = new music("track" + 1, "artista" + 1, 20000);
    lista[2] = new music("track" + 2, "artista" + 2, 50000);
    lista[3] = new music("track" + 3, "artista" + 3, 25000);
    lista[4] = new music("track" + 4, "artista" + 4, 30000);
}

public music getMusica(int indice){
    return this.lista[indice];
}
public String getNomeMusica(int indice){
    return this.lista[indice].getNomeMusica();
}
}