package Domain;

public class Zi extends Entity{
    private String lista;

    private int nrPasi;
    private int nrSomn;

    public Zi(int data, int nrPasi, int nrSomn,String lista) {
        super(data);
        this.nrPasi = nrPasi;
        this.nrSomn = nrSomn;
        this.lista = lista;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }


    public int getNrPasi() {
        return nrPasi;
    }

    public int getNrSomn() {
        return nrSomn;
    }

    @Override
    public String toString()
    {
        return "data: " + data + ", nr Pasi: " + nrPasi + " ,nr Somn: " + nrSomn + " ,lista activitati: " + lista;
    }
}
