package com.leanway.greendao.db;

import com.leanway.greendao.application.GreendaoApp;
import com.leanway.greendao.bean.DaoMaster;
import com.leanway.greendao.bean.DaoSession;
import com.leanway.greendao.bean.User;
import com.leanway.greendao.bean.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/5/24 11:28
 */

public class DBUser {

    /**
     * 插入一条记录
     *
     * @param user
     */
    public static void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public static void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public static void deleteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public static void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public static List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public static List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.eq(age));
        List<User> list = qb.list();
        return list;
    }
}
