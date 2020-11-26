package mx.uaemtex.ico.erp.common.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tmsg
 */
public class JpaRepository<T, PK> {

    @PersistenceContext(unitName = "ERP_PU")
    protected EntityManager entityManager;

    private Class<T> entityClass;

    public JpaRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public void deleteByPK(PK pk) {
        T ent = entityManager.find(entityClass, pk);
        entityManager.remove(entityManager.merge(ent));
    }

    public T findOne(PK id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> getList(String qryString, Object[] parameters) {
        Query query = entityManager.createQuery(qryString);
        int totalParameters = parameters.length;
        for (int paramNumber = 0; paramNumber < totalParameters; paramNumber++) {
            query.setParameter(paramNumber + 1, parameters[paramNumber]);
        }
        
        return query.getResultList();
    }

    public List<T> getList(String qryString, List<ParametroValor> parametros) {
        Query query = entityManager.createQuery(qryString);

        parametros.forEach((pv) -> {
            query.setParameter(pv.getNombreParam(), pv.getValor());
        });

        //query.setFirstResult(1);
        //query.setMaxResults(20);
        return query.getResultList();
    }

    
    public List<T> getListLimitada(String qryString, Object[] parameters, int inicio, int cuantos) {
        Query query = entityManager.createQuery(qryString);
        int totalParameters = parameters.length;
        for (int paramNumber = 0; paramNumber < totalParameters; paramNumber++) {
            query.setParameter(paramNumber + 1, parameters[paramNumber]);
        }
        query.setFirstResult(inicio);
        query.setMaxResults(cuantos);
        return query.getResultList();
    }

    public T getEntity(String qryString, Object[] parameters) {
        Query query = entityManager.createQuery(qryString);
        int totalParameters = parameters.length;
        for (int paramNumber = 0; paramNumber < totalParameters; paramNumber++) {
            query.setParameter(paramNumber + 1, parameters[paramNumber]);
        }
        T result = null;
        try {
            result = (T) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<T> getList(String qryString, Object[] parameters, List<String> values) {
        Query query = entityManager.createQuery(qryString);
        int totalParameters = parameters.length;
        for (int paramNumber = 0; paramNumber < totalParameters; paramNumber++) {
            query.setParameter(paramNumber + 1, parameters[paramNumber]);
        }
        StringBuilder list = new StringBuilder();
        list.append("(");
        int totalValues = values.size() - 1;
        int index = 0;
        for (String value : values) {
            list.append("'").append(value).append("'");
            if (index < totalValues) {
                list.append(",");
            }
        }
        list.append(")");
        query.setParameter(totalParameters + 1, values);

        return query.getResultList();
    }

    public Long getLongFromNativeQuery(String qryString) {
        Long value = (Long) entityManager.createNativeQuery(qryString).getSingleResult();
        return value;
    }

}
