package app.randomuser.tabsquare.model

import app.randomuser.tabsquare.RandomUserApp
import app.randomuser.tabsquare.vo.api.Result
import app.randomuser.tabsquare.vo.db.DaoSession
import app.randomuser.tabsquare.vo.db.UsersData
import app.randomuser.tabsquare.vo.db.UsersDataDao
import io.reactivex.annotations.NonNull

class UserDataModel (val daoSession: DaoSession) {
    val mEntityDao = daoSession.usersDataDao

    init {
        RandomUserApp.appComponent.plus(this)
    }

    fun getAllUserData() : List<UsersData> {
        return mEntityDao.queryBuilder()
                .orderAsc(UsersDataDao.Properties.Resultid)
                .list()
    }

    fun getUserData(page: String) : List<UsersData>? {
        val data = mEntityDao.queryBuilder()
                .where(UsersDataDao.Properties.Page.eq(page))
                .list()
        return if(data.size > 0){
            data
        }else{
            null
        }
    }

    fun saveUserData(@NonNull usersData: UsersData){
        mEntityDao.insertOrReplace(usersData)
    }

    fun deleteAllData(){
        mEntityDao.deleteAll()
    }
}