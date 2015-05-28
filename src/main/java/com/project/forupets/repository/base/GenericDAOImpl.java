/**
 *
 */
package com.project.forupets.repository.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

/**
 * @author VyTKSE60964
 */
public abstract class GenericDAOImpl<T, K extends Serializable> extends BaseDAO implements GenericDAO<T, K> {
    private Class<T> type;

    protected Class<T> getType() {
        return this.type;
    }

    protected String getClassName() {
        return type.getName();
    }

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public K save(T t) {
        return (K) getSession().save(t);
        // return t;
    }


    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }


    public void update(T t) {
        getSession().update(t);
        // getSession().flush();
    }


    public void delete(T t) {
        getSession().delete(t);
    }


    @SuppressWarnings("unchecked")
    public T find(K id) {
        return (T) getSession().get(type, id);
    }


    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query query = getSession().createQuery("from " + type.getSimpleName());
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public List<T> findAllWithOrder(String column, String orderType) {
        if (orderType == null && orderType.equals("")) {
            orderType = "ASC";
        }
        Query query = getSession().createQuery(
                String.format("from %s order by %s %s", type.getSimpleName(),
                        column, orderType));
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public List<T> findByProp(String prop, Object data) {
        Criteria cr = getSession().createCriteria(type);
        cr.add(Restrictions.eq(prop, data));
        return cr.list();
    }


    @SuppressWarnings("unchecked")
    public List<T> findByProps(Map<String, Object> props) {
        Criteria cr = getSession().createCriteria(type);
        for (Map.Entry<String, Object> entry : props.entrySet()) {
            cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        return cr.list();
    }


    @SuppressWarnings("unchecked")
    public List<Object[]> listAll(String[] columns) {
        if (columns != null) {
            StringBuffer hql = new StringBuffer("select ");
            for (String str : columns) {
                hql.append(str).append(",");
            }
            hql = hql.delete(hql.length() - 1, hql.length());
            hql = hql.append(" FROM ").append(type.getSimpleName());

            Query query = getSession().createQuery(hql.toString());
            return query.list();
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public List<Object[]> listAll(String[] columns, String[] names) {
        if (columns != null) {
            StringBuffer hql = new StringBuffer("select ");
            for (int i = 0; i < columns.length; i++) {
                hql.append(columns[i]).append(" as ").append(names[i])
                        .append(",");
            }
            hql = hql.delete(hql.length() - 1, hql.length());
            hql = hql.append(" FROM ").append(type.getSimpleName());

            Query query = getSession().createQuery(hql.toString());
            System.out.println(query.toString());
            return query.list();
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public List<Object[]> listAll(String[] columns, int startIndex, int pageSize) {
        if (columns != null) {
            StringBuffer hql = new StringBuffer("select ");
            for (String str : columns) {
                hql.append(str).append(",");
            }
            hql = hql.delete(hql.length() - 1, hql.length());
            hql = hql.append(" FROM ").append(type.getSimpleName());

            Query query = getSession().createQuery(hql.toString());
            query.setFirstResult(startIndex);
            query.setMaxResults(pageSize);
            return query.list();
        }
        return null;
    }


    public void update(String idFieldName, String orderFieldName,
                       Map<Long, Integer> orders, int batchSize) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }

        String hql = "UPDATE " + type.getSimpleName() + " SET "
                + orderFieldName + " = ? WHERE " + idFieldName + " = ?";
        Query q = getSession().createQuery(hql);
        Set<Long> keys = orders.keySet();

        List<Long> list = new ArrayList<Long>(keys);
        int max = list.size();
        for (int i = 0; i < max; i++) {
            q.setInteger(0, orders.get(list.get(i)));
            q.setLong(1, list.get(i));
            q.executeUpdate();

            if (i % batchSize == 0) {
                getSession().flush();
                getSession().clear();
            }

        }

        getSession().flush();
        getSession().clear();
    }
}
