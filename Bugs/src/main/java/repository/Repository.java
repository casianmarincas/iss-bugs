package repository;

public interface Repository<T, ID> {

    void add(T element);
    void delete(T element);
    void update(T element);
    T findOne(ID elementId);
    Iterable<T> findAll();
}
