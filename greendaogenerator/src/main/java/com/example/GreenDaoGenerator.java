package com.example;


import de.greenrobot.daogenerator.*;

public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(4, "com.example.tonydemo.greendao.dao")d;

        addNote(schema);
        addCustomerOrder(schema);

        new DaoGenerator().generateAll(schema, "/home/tony/code/myDemo/tonyDemo/src/main/java-gen");
    }

    private static void addNote(Schema schema)
    {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema)
    {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();  //必须设置,否则order外键关联此失败
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").primaryKey().getProperty();
        order.addToOne(customer, customerId);

//        ToMany customerToOrders = customer.addToMany(order, customerId);
//        customerToOrders.setName("orders");
//        customerToOrders.orderAsc(orderDate);
    }
}
