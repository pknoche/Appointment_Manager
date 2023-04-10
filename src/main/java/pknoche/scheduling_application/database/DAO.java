package pknoche.scheduling_application.database;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO<T> {
    void Create(T t);
    void Read(T t);
    void Update(T t);
    void Delete(T t);
    ObservableList<T> getAll() throws SQLException;
}
