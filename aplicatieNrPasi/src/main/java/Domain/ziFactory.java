package Domain;

public class ziFactory implements IEntityFactory<Zi>{
    @Override
    public Zi createEntity(String line) {
        int data = Integer.parseInt(line.split(",")[0]);
        int nrPasi = Integer.parseInt(line.split(",")[1]);
        int nrSomn = Integer.parseInt(line.split(",")[2]);
        String lista = line.split(",")[3];
        return new Zi(data, nrPasi,nrSomn,lista);
    }
}
