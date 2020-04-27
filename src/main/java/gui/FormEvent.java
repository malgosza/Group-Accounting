package gui;

public class FormEvent{

    private String imie;
    private String kwota;

    public FormEvent(String imie, String kwota) {
        this.imie=imie;
        this.kwota=kwota;
    }

    public String getImie() {
        return imie;
    }

    public String getKwota() {
        return kwota;
    }
}
