package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.PatrimonioDAO;
import br.info.pweb.chines.models.Patrimonio;

@Repository
@Transactional
public class PatrimonioJPA implements PatrimonioDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Patrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);		
	}

	@Override
	public void excluir(Patrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void alterar(Patrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Patrimonio buscar(Long id) {
		String hql = "FROM Patrimonio p WHERE p.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Patrimonio> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Patrimonio buscarNome(String nome) {
		String hql = "FROM Patrimonio p WHERE p.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		@SuppressWarnings("unchecked")
		List<Patrimonio> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Patrimonio> listar() {
		String hql = "FROM Patrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

}
