package com.leanway.greendao.db;

import com.leanway.greendao.application.GreendaoApp;
import com.leanway.greendao.bean.City;
import com.leanway.greendao.bean.CityDao;
import com.leanway.greendao.bean.DaoMaster;
import com.leanway.greendao.bean.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author Xibo_Yue
 * @time 2017/5/24 11:28
 */

public class DBCity {
    /**
     * 插入一条记录
     *
     * @param city
     */
    public static void insertCity(City city) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        cityDao.insert(city);
    }

    /**
     * 插入用户集合
     *
     * @param citys
     */
    public static void insertCityList(List<City> citys) {
        if (citys == null || citys.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        cityDao.insertInTx(citys);
    }

    /**
     * 删除一条记录
     *
     * @param city
     */
    public static void deleteCity(City city) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        cityDao.delete(city);
    }

    /**
     * 更新一条记录
     *
     * @param city
     */
    public static void updateCity(City city) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        cityDao.update(city);
    }

    /**
     * 查询用户列表
     */
    public static List<City> queryCityList() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        QueryBuilder<City> qb = cityDao.queryBuilder();
        List<City> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public static List<City> queryCityList(int num) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(GreendaoApp.getInstance()).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityDao cityDao = daoSession.getCityDao();
        QueryBuilder<City> qb = cityDao.queryBuilder();
        qb.where(CityDao.Properties.Num.eq(num));
        List<City> list = qb.list();
        return list;
    }
}
