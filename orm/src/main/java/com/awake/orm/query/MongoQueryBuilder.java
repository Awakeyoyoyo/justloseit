package com.awake.orm.query;

import com.awake.orm.OrmContext;
import com.awake.orm.model.IEntity;
import com.awake.orm.model.Pair;
import com.awake.util.base.StringUtils;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version : 1.0
 * @ClassName: MongoQueryBuilder
 * @Description: 查询接口  建造者模式
 * @Auther: awake
 * @Date: 2023/11/22 18:25
 **/
public class MongoQueryBuilder<E extends IEntity<?>> implements IQueryBuilder<E> {

    private final Class<E> entity;
    private Bson builder = Filters.empty();

    public MongoQueryBuilder(Class<E> entityClazz) {
        entity = entityClazz;
    }

    private void wrapBuilder(Bson bson) {
        builder = Filters.and(builder, bson);
    }

    @Override
    public IQueryBuilder<E> eq(String fieldName, Object fieldValue) {
        var bson = Filters.eq(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> ne(String fieldName, Object fieldValue) {
        var bson = Filters.ne(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> lt(String fieldName, Object fieldValue) {
        var bson = Filters.lt(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> lte(String fieldName, Object fieldValue) {
        var bson = Filters.lte(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> gt(String fieldName, Object fieldValue) {
        var bson = Filters.gt(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> gte(String fieldName, Object fieldValue) {
        var bson = Filters.gte(fieldName, fieldValue);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> in(String fieldName, List<?> fieldValueList) {
        var bson = Filters.in(fieldName, fieldValueList);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> nin(String fieldName, List<?> fieldValueList) {
        var bson = Filters.nin(fieldName, fieldValueList);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public IQueryBuilder<E> like(String fieldName, String fieldValue) {
        var regex = StringUtils.format("^{}.*", fieldValue);
        var bson = Filters.regex(fieldName, regex);
        wrapBuilder(bson);
        return this;
    }

    @Override
    public List<E> queryAll() {
        var collection = OrmContext.getOrmManager().getCollection(entity);
        var list = new ArrayList<E>();
        var result = collection.find(builder);
        result.forEach(new Consumer<IEntity<?>>() {
            @Override
            public void accept(IEntity<?> entity) {
                @SuppressWarnings("unchecked")
                var e = (E) entity;
                list.add(e);
            }
        });
        return list;
    }

    @Override
    public Pair<Page, List<E>> queryPage(int page, int itemsPerPage) {
        var collection = OrmContext.getOrmManager().getCollection(entity);

        var p = Page.valueOf(page, itemsPerPage, collection.countDocuments());

        var result = collection.find(builder);
        var list = new ArrayList<E>();
        result.skip(p.skipNum())
                .limit(p.getItemsPerPage())
                .forEach(new Consumer<IEntity<?>>() {
                    @Override
                    public void accept(IEntity<?> entity) {
                        @SuppressWarnings("unchecked")
                        var e = (E) entity;
                        list.add(e);
                    }
                });

        return new Pair<>(p, list);
    }

    @Override
    public E queryFirst() {
        var collection = OrmContext.getOrmManager().getCollection(entity);
        return collection.find(builder).first();
    }
}
