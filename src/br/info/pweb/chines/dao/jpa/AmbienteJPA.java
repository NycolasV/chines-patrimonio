package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.AmbienteDAO;
import br.info.pweb.chines.models.Ambiente;

@Repository
@Transactional
public class AmbienteJPA implements AmbienteDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Ambiente obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void excluir(Ambiente obj) {
		sessionFactory.getCurrentSession().delete(obj);	
	}

	@Override
	public void alterar(Ambiente obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Ambiente buscar(Long id) {
		String hql = "FROM Ambiente a WHERE a.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Ambiente> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Ambiente buscarNome(String nome) {
		String hql = "FROM Ambiente a WHERE a.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		@SuppressWarnings("unchecked")
		List<Ambiente> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Ambiente> listar() {
		String hql = "FROM Ambiente u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

}
