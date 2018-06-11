package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.ItemPatrimonioDAO;
import br.info.pweb.chines.models.ItemPatrimonio;

@Repository
@Transactional
public class ItemPatrimonioJPA implements ItemPatrimonioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void excluir(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void alterar(ItemPatrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public ItemPatrimonio buscar(Long id) {
		String hql = "FROM ItemPatrimonio i WHERE i.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<ItemPatrimonio> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ItemPatrimonio> buscarPatrimonio(Long id) {
		String hql = "FROM itemPatrimonio i WHERE i.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ItemPatrimonio> listar() {
		String hql = "FROM ItemPatrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	
}
