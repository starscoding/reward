package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.jpa.DaoSupport;
import com.smile.azxx.util.ListUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smile on 2018/4/6.
 */
@Component
@Transactional
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext
    public EntityManager em;

//    private Session getCurrentSession(){
//        return this.sessionFactory.openSession();
//    }

//    public SysUserEntity load(Serializable id) {
//        return (SysUserEntity) getCurrentSession().load(SysUserEntity.class,id);
//    }

    public User get(long id) {
        return  em.find(User.class,id);
//        return (SysUserEntity) getCurrentSession().get(SysUserEntity.class,id);
    }

    @Override
    public List<String> getRoles(String userId) {
        Query query = this.getEm().createNativeQuery("SELECT role_id FROM sys_user_role WHERE user_id=?");
        query.setParameter(1,userId);
        return query.getResultList();
    }
    @Override
    public List<String> getRolesByUserName(String userName) {
        StringBuffer sql = new StringBuffer("SELECT c.value FROM sys_user_role a");
        sql.append(" left join sys_user b on a.user_id=b.id");
        sql.append(" left join sys_role c on a.role_id=c.id");
        sql.append(" where b.username=?");
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        return query.getResultList();
    }

    @Override
    public List<String> getResource(String roleId) {
        Query query = this.getEm().createNativeQuery("SELECT resource_id FROM sys_role_resource WHERE role_id=?");
        query.setParameter(1,roleId);
        return query.getResultList();
    }

    @Override
    public List<String> getResourceByRoleName(String roleName) {
        StringBuffer sql = new StringBuffer("SELECT c.value FROM sys_role_resource a");
        sql.append(" left join sys_role b on a.role_id=b.id");
        sql.append(" left join sys_resource c on a.resource_id=c.id");
        sql.append(" WHERE b.value=?");
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,roleName);
        return query.getResultList();
    }

    @Override
    public User getUserByName(String userName) {
        User user = new User();
        log.info(userName);
        Query query = this.getEm().createNativeQuery("SELECT * FROM sys_user WHERE username=?",User.class);
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(ListUtil.isNotBlank(list)){
            user = (User) list.get(0);
        }
        return user;
    }

    @Override
    public List<User> getUsers(String userName) {
        log.info(userName);
        Query query = this.getEm().createQuery("SELECT u FROM User u WHERE u.username=?1",User.class);
        query.setParameter(1,userName);
        return query.getResultList();
    }

    @Override
    public void updateContact(String wechat, String qq,String userName) {
        String sql = "update sys_user set wechat=?,qq=? where username=?";
        log.info(sql);
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,wechat);
        query.setParameter(2,qq);
        query.setParameter(3,userName);
        query.executeUpdate();
    }

    @Override
    public void alterPwd(String newPwd,String userName) {
        String sql = "update sys_user set password=? where username=?";
        log.info(sql);
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,newPwd);
        query.setParameter(2,userName);
        query.executeUpdate();
    }
    @Override
    public void alterFetchPwd(String newPwd,String userName) {
        String sql = "update sys_user set fetch_pwd=? where username=?";
        log.info(sql);
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,newPwd);
        query.setParameter(2,userName);
        query.executeUpdate();
    }

    public List<User> getUserByPName(String userName) {
        String sql = "select u from User u where u.parentName=?1";
        Query query = this.getEm().createQuery(sql,User.class);
        query.setParameter(1,userName);
        return query.getResultList();
    }

    public void delUserByNames(String[] userNames){
        String sql = "delete from User u where u.username in ?1";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,Arrays.asList(userNames));
        query.executeUpdate();
    }
}
