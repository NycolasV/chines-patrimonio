package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.CategoriaDAO;
import br.info.pweb.chines.models.Categoria;

@Repository
@Transactional
public class CategoriaJPA implements CategoriaDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Categoria obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void excluir(Categoria obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void alterar(Categoria obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Categoria buscar(Long id) {
		String hql = "FROM Categoria c WHERE c.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Categoria> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Categoria buscarNome(String nome) {
		String hql = "FROM Categoria c WHERE c.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		@SuppressWarnings("unchecked")
		List<Categoria> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Categoria> listar() {
		String hql = "FROM Categoria";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

}
