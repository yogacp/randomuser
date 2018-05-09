package app.randomuser.tabsquare.utils

class AppConstants {

    /**
     * Properties of database
     * */
    interface database {
        companion object {
            val databaseName: String = "randomuserdb"
        }
    }

    /**
     * Gender
     */
    interface GENDER {
        companion object {
            val MALE = "male"
            val FEMALE = "female"
        }
    }
}