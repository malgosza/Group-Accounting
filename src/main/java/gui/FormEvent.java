package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String imie;
    private String kwota;

    public FormEvent(Object source){
        super(source);
    }

    public FormEvent(Object source, String imie, String kwota) {
        super(source);
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
