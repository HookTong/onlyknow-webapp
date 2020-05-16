package com.onlyknow.platform.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OKBaseManager {
    private final static String TAG = "OKBaseManager";

    private final static SessionFactory FACTORY = builderFactory();

    // ThreadLocal<Session> 线程锁 为了单线程安全
    private final static ThreadLocal<Session> THREAD_LOCAL = new ThreadLocal<>();

    protected Session open() {

        Session session = THREAD_LOCAL.get();

        if (session == null) {
            session = FACTORY.openSession();
            THREAD_LOCAL.set(session);
        }

        return session;

    }

    public void close() {

        Session session = THREAD_LOCAL.get();

        if (session != null) {

            session.close();

            THREAD_LOCAL.set(null);

        }
    }

    private static SessionFactory builderFactory() {
        Configuration conf = new Configuration().configure();
        return conf.buildSessionFactory();
    }
}
