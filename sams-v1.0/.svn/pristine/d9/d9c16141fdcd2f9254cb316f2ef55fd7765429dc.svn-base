package com.sams.common.scanner;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.sitco.yt.manage.service.DatabaseContext;

@Component
public class ThreadDatabaseConext {
    @Lookup
    public  DatabaseContext getNewDatabaseContext() {
        return new DatabaseContext();
    }

    private  ThreadLocal<DatabaseContext> databaseContextThreadLocal = new ThreadLocal<DatabaseContext>(){
        public DatabaseContext initialValue(){
            return getNewDatabaseContext();
        }
    };

    public DatabaseContext getThreadDatabaseContext(HttpSession httpSession) {
        DatabaseContext databaseContext = databaseContextThreadLocal.get();
        //String userCode = (String)httpSession.getAttribute(Session.USER_CODE);
        //databaseContext.setUserCode(userCode);
        return databaseContext;
    }

    public   DatabaseContext getThreadDatabaseContext() {
        DatabaseContext databaseContext = databaseContextThreadLocal.get();
        //SSOUser ssoUser = UserHolder.getUser();
        String userCode = "";
        databaseContext.setUserCode(userCode);
        return databaseContext;
    }
}
