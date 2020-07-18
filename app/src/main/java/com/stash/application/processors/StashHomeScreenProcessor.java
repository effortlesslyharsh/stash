package com.stash.application.processors;
/*
 * @author harsh mishra
 * */
import com.stash.application.constants.StashConstants;
import com.stash.application.utility.StashFirestoreDBUtilities;

public class StashHomeScreenProcessor {


    /**
     * @return Dsiplay message for home screen text view
     */
    public String getGreetingMessageForHome() {
        String fetchTag = StashConstants.FIRESTORE_DB_COLLECTION_FIELD_TAGS.GENERAL.HOME_WELCOME_MESSAGE;
        return StashFirestoreDBUtilities.getDisplayMessageForWelcomeScreen(fetchTag);
    }

}
