package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Entity> implements IRepository<T> {
    List<T> entities = new ArrayList<T>();
    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
        {
            throw new IllegalArgumentException("Entity can't be null!");
        }
        if (find(entity.getData()) != null) {
            throw new DuplicateEntityException("Entity already exists!");
        }
        entities.add(entity);
    }

    @Override
    public void remove(int id) {
        for (T entity :  entities)
        {
            if (entity.getData() == id)
            {
                entities.remove(entity);
                return;
            }
        }
    }

    @Override
    public void update(T entity) {
        for(int i = 0; i<entities.size();i++){
            if(entities.get(i).getData() == entity.getData()){
                entities.set(i,entity);
            }
        }
    }

    @Override
    public T find(int id) {
        for (T entity :  entities)
        {
            if (entity.getData() == id)
            {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }
}
