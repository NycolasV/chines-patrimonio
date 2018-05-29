package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.MovimentacaoDAO;
import br.info.pweb.chines.models.Movimentacao;

@Repository
@Transactional
public class MovimentacaoJPA implements MovimentacaoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Movimentacao obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void excluir(Movimentacao obj) {
		sessionFactory.getCurrentSession().delete(obj);		
	}

	@Override
	public void alterar(Movimentacao obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Movimentacao buscar(Long id) {
		String hql = "FROM Movimentacao m WHERE m.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Movimentacao> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movimentacao> listar() {
		String hql = "FROM Movimentacao";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
}
