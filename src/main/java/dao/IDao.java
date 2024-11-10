package dao;

import java.util.List;

//POR QUÉ USAMOS EL <T> ? Para poder re utilizar esta interfaz con distintos tipos de objetos.
//Por ejemplo actualmente tenemos el objeto DENTIST (El cual reemplazara a la <T> en la implementación),
//pero si tuvieramos otro objeto, como PATIENT, tendriamos que crear otra interfaz exactamente igual, pero que
//usa el tipo <Patient>, al usar el generico creamos una sola interfaz y luego colocamos el objeto que querramos.

public interface IDao <T>{
    T save(T t); //GUARDA EL OBJETO (T)
    T findById(int id); //ENCUENTRA UN OBJETO POR ID RETORNA UN OBEJTO (T)
    void update(T t); //ACTUALIZA EL OBJETO Y NO RETORNA NADA POR ESO VOID
    void delete(int id);//BORRA EL OBJETO POR ID 
    List<T> findAll(); //DEVUELVE UNA LISTA DE TIPO T CON TODOS LOS OBEJTOS
}
