# lambda写法-快速上手



> 参考文档

* [官方动态sql介绍](https://mybatis.org/mybatis-dynamic-sql/docs/introduction.html)







①②③④⑤⑥⑦⑧⑨

# 1. where条件

```
import static org.mybatis.dynamic.sql.SqlBuilder.*;
```

## 1.1 count

查询 `roleId in (.....)` 的数量

```java
private boolean checkRoleIds(Integer[] roleIds){
    Long ren = roleDao.count(c-> c.where(RoleDynamicSqlSupport.roleId,isIn(roleIds)));
    if(roleIds.length>0 && ren.intValue()==roleIds.length){
        return true;
    }
    throw new BusinessException("角色编号在数据库中没有找到:"+ Arrays.toString(roleIds));
}
```



## 1.2 delete

删除`userId=?`记录

```java
//删除用户原有的权限
userRoleDao.delete(c->c.where(UserRoleDynamicSqlSupport.userId,isEqualTo(changeUserId)));
```



## 1.3 select

在用户登陆的时候，通过用户名 email phone来查询用户。

```java
public Optional<User> selectUserByAccount(String account){
    if(StringUtils.isEmpty(account)){
        throw new BusinessException("account is not null");
    }
    return userDao.selectOne(c->
                             c.where(UserDynamicSqlSupport.username,isEqualTo(account))
                             .or(UserDynamicSqlSupport.email,isEqualTo(account))
                             .or(UserDynamicSqlSupport.phone,isEqualTo(account))
                            );
}
```



## 1.4 common where

```java
    @Test
    public void testUpdate() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonMapper mapper = session.getMapper(PersonMapper.class);

            int rows = mapper.update(c ->
                c.set(occupation).equalToStringConstant("worker")
                .applyWhere(commonWhere));

            assertThat(rows).isEqualTo(3);
        }
    }

    private WhereApplier commonWhere =  d -> d.where(id, isEqualTo(1)).or(occupation, isNull());
```





# 2. orderBy

可以作一个公共的orderBy，这样调用起来更方便



## 2.1 common orderBy

```java
//公共的排序
SortSpecification[] commonOrderBy={gmtModified.descending()};

public List<PsOrder> selectMyCheckOrder(Long myId) {
    return psOrderDao.select(c->c
                             .where(userId,isEqualTo(myId))
                             .and(orderStatus,isIn(PsOrderStatus.MY_DEAL_ORDERS_STATUS))
                             .orderBy(commonOrderBy)
                            );
}
```





# 3. jion

多表查询，如果Sql语句比较复杂，那么建议在Dao中直接写Sql语句。

如果比较简单，那么可以采用下面的第一种方法。



## 3.1 commonMapper

> 查询一个列表：`commonMapper.selectMany`

```java
    @Autowired
    private CommonMapper commonMapper;
    /**
     * 两个表Json查询出List
     */
    @Test
    public void selectManyTest(){
        List<BasicColumn> columns =Lists.newArrayList(userDao.selectList);
        columns.add(nickname);

        SelectStatementProvider selectStatement = select(columns)
                .from(user)
                .join(userEx).on(user.userId,equalTo(userEx.userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        System.out.println(selectStatement.getSelectStatement());
        List<Map> aaa = commonMapper.selectMany(selectStatement);
    }
```



> 查询一条记录：`commonMapper.selectOne`

```java
    @Autowired
    private CommonMapper commonMapper;    
   /**
     * 两个表Json查询出一条记录
     */
    @Test
    public void selectOneTest(){

        List<BasicColumn> columns =Lists.newArrayList(userDao.selectList);
        columns.add(nickname);

        SelectStatementProvider selectStatement = select(columns)
                .from(user)
                .join(userEx).on(user.userId,equalTo(userEx.userId))
                .where(UserExDynamicSqlSupport.userId,isEqualTo(1L))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        System.out.println(selectStatement.getSelectStatement());
        Optional<Map>  one = commonMapper.selectOne(selectStatement);
        System.out.println(one.get());
    }
```



# 4. select



## 4.1 countDistinct



```java
SelectStatementProvider selectStatement = select(countDistinct(lastName).as("count"))
    .from(person)
    .build()
    .render(RenderingStrategies.MYBATIS3);

String expected = "select count(distinct last_name) as count from Person";
assertThat(selectStatement.getSelectStatement()).isEqualTo(expected);
```





# 5. update



## 5.1 传入对象

使用`mapper`中的`updateSelectiveColumns`或者`updateAllColumns`



```java
rows = mapper.update(c ->
                     PersonMapper.updateAllColumns(record, c)
                     .where(id, isEqualTo(100))
                     .and(firstName, isEqualTo("Joe")));
```



## 5.2 单独set

```java
int rows = mapper.update(c ->
                         c.set(occupation).equalToStringConstant("worker")
                         .applyWhere(commonWhere));
```



