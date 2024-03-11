package Service;

import Domain.Zi;
import Repository.DuplicateEntityException;
import Repository.IRepository;

import java.util.Collection;

public class ZiService {
    IRepository<Zi> repository;

    public ZiService(IRepository<Zi> repository)
    {
        this.repository = repository;
    }

    public void add(int id, int nrPasi, int nrSomn,String lista) throws DuplicateEntityException {
        repository.add(new Zi(id, nrPasi, nrSomn,lista));
    }

    public void update(int id, int nrPasi, int nrSomn,String lista) throws DuplicateEntityException {
        repository.update(new Zi(id, nrPasi, nrSomn,lista));
    }

    public void remove(int id){
        repository.remove(id);
    }

    public Collection<Zi> getAll()
    {
        return repository.getAll();
    }
}

