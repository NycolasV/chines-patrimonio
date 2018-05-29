package br.info.pweb.chines.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.info.pweb.chines.dao.UsuarioDAO;
import br.info.pweb.chines.models.Usuario;

@Repository
@Transactional
public class UsuarioJPA implements UsuarioDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Usuario obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}
	
	@Override
	public void excluir(Usuario obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void alterar(Usuario obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Usuario buscar(Long id) {
		String hql = "FROM Usuario u WHERE u.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Usuario> resultList = query.list();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Usuario buscarEmail(String email) {
		String hql = "FROM Usuario u WHERE u.email = :email";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
				
		@SuppressWarnings("unchecked")
		List<Usuario> resultList = query.list();
				
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Usuario buscarEmailSenha(String email, String senha) {
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
				
		@SuppressWarnings("unchecked")
		List<Usuario> resultList = query.list();
				
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		String hql = "FROM Usuario u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

}
