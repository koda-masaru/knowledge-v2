package org.support.project.knowledge.deploy.v1_12;

import org.support.project.knowledge.deploy.Migrate;
import org.support.project.ormapping.tool.dao.InitializeDao;

public class Migrate_1_12_2 implements Migrate {

    public static Migrate_1_12_2 get() {
        return org.support.project.di.Container.getComp(Migrate_1_12_2.class);
    }

    @Override
    public boolean doMigrate() throws Exception {
        InitializeDao initializeDao = InitializeDao.get();
        String[] sqlpaths = {
            "/org/support/project/knowledge/deploy/v1_12/migrate_v1_12_2.sql",
        };
        initializeDao.initializeDatabase(sqlpaths);
        return true;
    }
}