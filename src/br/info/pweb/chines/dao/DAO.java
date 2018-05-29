package br.info.pweb.chines.dao;

import java.util.List;

public interface DAO<T>{

	public void persistir(T obj);
	
	public void excluir(T obj);

	public void alterar(T obj);
	
	public T buscar(Long id);
	
	public List<T> listar();
	
}
