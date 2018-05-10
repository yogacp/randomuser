package app.randomuser.tabsquare.model

import app.randomuser.tabsquare.RandomUserApp
import app.randomuser.tabsquare.vo.db.*
import io.reactivex.annotations.NonNull

class UserDetailDataModel (val daoSession: DaoSession) {
    val mEntityDao = daoSession.userDetailDataDao

    init {
        RandomUserApp.appComponent.plus(this)
    }

    fun getUserData(query: String) : List<UserDetailData>? {
        val data = mEntityDao.queryBuilder()
                .where(UserDetailDataDao.Properties.Md5.eq(query))
                .list()
        return if(data.size > 0){
            data
        }else{
            null
        }
    }

    fun saveUserData(@NonNull userDetailData: UserDetailData){
        mEntityDao.insertOrReplace(userDetailData)
    }

    fun delete(userHash: String) {
        val deleteQuery = mEntityDao.queryBuilder()
                .where(UserDetailDataDao.Properties.Md5.eq(userHash))
                .buildDelete()
        deleteQuery.executeDeleteWithoutDetachingEntities()
        daoSession.clear()
    }

    fun deleteAllData(){
        mEntityDao.deleteAll()
    }
}