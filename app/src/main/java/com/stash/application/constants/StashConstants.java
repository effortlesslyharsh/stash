package com.stash.application.constants;
/*
 * @author harsh mishra
 * */
public class StashConstants {

    public static final String LOG_TAG = ":::::::::::" ;
    public static final Long DB_FETCH_TIMEOUT_MILLISECONDS = new Long(15000);
    public static class UI_MESSAGES{
        public static class ERROR_MESSAGE{
            public static final String OOPS ="oops something went wrong";
        }
    }

    public static class FIRESTORE_DB_COLLECTIONS{
        public static  final  String GENERAL = "general";
    }

    public static class FIRESTORE_DB_COLLECTION_FIELD_TAGS{
        public static  final  class GENERAL{
            public  static final String HOME_WELCOME_MESSAGE ="home_welcome_message";
            public  static final String STATES_DATA ="states_data";
        }
    }

}
