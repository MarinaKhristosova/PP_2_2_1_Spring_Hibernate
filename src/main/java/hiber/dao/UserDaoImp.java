package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("uncheked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      String hql = "FROM User where car.model=:model and car.series=:series";
      Query<User> query = sessionFactory.getCurrentSession().createQuery(hql)
              .setParameter("model", model)
              .setParameter("series", series);

      return query.setMaxResults(1).uniqueResult();
   }
}
