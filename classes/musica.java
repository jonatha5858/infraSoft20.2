package Classes;

public class musica{
    private String Musica;
    private float TempMusica;
    private String nomeArtista;
    //coloquei o temp de musica como float pois não sei ainda se ferei as musicas 100% int
    //nMSC NOME MUSICA
    //tmpMsc tempo de musica
    

    public music(String nMsc, String nArtista, int tmpMsc){
        this.Musica = nMsc;
        this.TempMusica = tmpMsc;
        this.nomeArtista = nArtista;
        
    }
    public String getNomeMusica() {
        return Musica;
    }
    public String getNomeArtista() {
        return nomeArtista;
    }
    public int getDuracaoMusica() {
        return TempMusica;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }
    
    public void setDuracaoMusica(int TempMusica) {
        this.TempMusica = TempMusica;
    }

}