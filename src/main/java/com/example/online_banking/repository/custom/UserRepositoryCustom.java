package com.example.online_banking.repository.custom;

import com.example.online_banking.model.LoansPackage;
import com.example.online_banking.model.User;
import com.example.online_banking.rest.model.Column;
import com.example.online_banking.rest.model.Order;
import com.example.online_banking.rest.model.PagingRequest;
import com.example.online_banking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public List<User> getUserList(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM USER u JOIN USER_ROLE ur ");
        sqlBuilder.append(" ON u.id = ur.user_id ");
        sqlBuilder.append(" WHERE ur.ROLE_NAME = 'ROLE_USER' ");
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }
        Order order = paging.getOrder()
                .get(0);

        int columnIndex = order.getColumn();
        Column column = paging.getColumns()
                .get(columnIndex);
        if (column != null) {
            sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString(), User.class);
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(paging.getStart())
                .setMaxResults(paging.getLength());
        return query.getResultList();
    }

    public Integer getTotalUser(PagingRequest paging) {
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM online_banking.USER WHERE 1 = 1");
        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
            sqlBuilder.append(" AND LOWER(FULL_NAME) like :key");
            parameter.put("key", "%" + paging.getSearch().getValue().toLowerCase() + "%");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.valueOf(query.getSingleResult().toString());
    }


    // TODO:
//    public List<LoansPackage> getLoansPackageList(PagingRequest paging) {
//        Map<String, Object> parameter = new HashMap<>();
//        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM LOANS_PACKAGE WHERE 1 = 1");
//        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
//            sqlBuilder.append(" AND DURATION like :key");
//            parameter.put("key", "%" + paging.getSearch().getValue() + "%");
//        }
//        Order order = paging.getOrder()
//                .get(0);
//
//        int columnIndex = order.getColumn();
//        Column column = paging.getColumns()
//                .get(columnIndex);
//        if (column != null) {
//            sqlBuilder.append(" ORDER BY ").append(CommonUtils.camelToSnake(column.getData())).append(" ").append(order.getDir());
//        }
//
//        Query query = entityManager.createNativeQuery(sqlBuilder.toString(), LoansPackage.class);
//        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
//            query.setParameter(entry.getKey(), entry.getValue());
//        }
//        query.setFirstResult(paging.getStart())
//                .setMaxResults(paging.getLength());
//        return query.getResultList();
//    }
//
//    public Integer getTotalLoansPackage(PagingRequest paging) {
//        Map<String, Object> parameter = new HashMap<>();
//        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM online_banking.LOANS_PACKAGE WHERE 1 = 1");
//        if (!CommonUtils.isNull(paging.getSearch().getValue())) {
//            sqlBuilder.append(" AND DURATION like :key");
//            parameter.put("key", "%" + paging.getSearch().getValue() + "%");
//        }
//
//        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
//        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
//            query.setParameter(entry.getKey(), entry.getValue());
//        }
//        return Integer.valueOf(query.getSingleResult().toString());
//    }
}
