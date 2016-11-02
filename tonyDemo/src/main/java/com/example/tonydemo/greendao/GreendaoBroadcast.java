package com.example.tonydemo.greendao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.tonydemo.Config;
import com.example.tonydemo.greendao.dao.*;

import java.util.Date;

/**
 * Created by tony on 16-9-19.
 */
public class GreendaoBroadcast extends BroadcastReceiver {
    public DaoSession daoSession;

    public DaoMaster daoMaster;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            if (action.equals(Config.ACTION_BROADCAST_INSERT_CUSTOMER1)) {
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                CustomerDao customerDao = daoSession.getCustomerDao();
                Customer customer = new Customer((long) 1, "1");
                customerDao.insert(customer);
            } else if (action.equals(Config.ACTION_BROADCAST_INSERT_ORDER1)) {
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                OrderDao orderDao = daoSession.getOrderDao();
                Order order = new Order(new Date(), (long) 1);
                orderDao.insert(order);
            } else if (action.equals(Config.ACTION_BROADCAST_DELETE_CUSTOMER1)) {
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                CustomerDao customerDao = daoSession.getCustomerDao();
                customerDao.deleteByKey((long) 1);
            } else if (action.equals(Config.ACTION_BROADCAST_DELETE_ORDER1)) {
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                OrderDao customerDao = daoSession.getOrderDao();
                customerDao.deleteByKey((long) 1);
            } else if (action.equals(Config.ACTION_BROADCAST_INSERT_ORDER2)) {
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                OrderDao orderDao = daoSession.getOrderDao();
                Order order = new Order(new Date(), (long) 2);
                orderDao.insert(order);
            }else if (action.equals(Config.ACTION_BROADCAST_UPDATE_ORDER2)){
                daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "notes-db", null).getWritableDatabase());
                daoSession = daoMaster.newSession();
                OrderDao orderDao=daoSession.getOrderDao();
                Order order=new Order(new Date(), (long) 6);
                orderDao.update(order);
            }
        }
    }
}
