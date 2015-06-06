package jordiarjan.databases.opdracht3.Repository;

import jordiarjan.databases.opdracht3.DBManager;

/**
 * Created by Arjan on 06/06/2015.
 */
public abstract class Repository {

    protected DBManager dbManager;

    public Repository(DBManager dbManager)
    {
        this.dbManager = dbManager;
    }
}
