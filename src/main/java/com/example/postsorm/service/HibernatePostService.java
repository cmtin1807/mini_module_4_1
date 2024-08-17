package com.example.postsorm.service;

import com.example.postsorm.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Service
public class HibernatePostService implements IPostService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> findAll() {
        String FindAllPostsQuery = "Select p from Post p";
        TypedQuery<Post> query = entityManager.createQuery(FindAllPostsQuery, Post.class);
        return query.getResultList();
    }

    @Override
    public Post findById(int id) {
        String FindPostByIdQuery = "Select p from Post p where p.id = :id";
        TypedQuery<Post> query = entityManager.createQuery(FindPostByIdQuery, Post.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Post post) {
        Transaction transaction = null;
        Post postSave = null;
        if (post.getId() == 0) {
            postSave = new Post();
        }
        else {
            postSave = findById(post.getId());
        }
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postSave.setTitle(post.getTitle());
            postSave.setDescription(post.getDescription());
            postSave.setContent(post.getContent());
            postSave.setImage(post.getImage());
            session.saveOrUpdate(postSave);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) {
        Post post = findById(id);
        if (post != null) {
            Transaction transaction = null;
            try(Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(post);
                transaction.commit();
            }
            catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }


    }
}
