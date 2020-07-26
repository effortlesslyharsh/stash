package com.stash.application.data.utility;

import com.stash.application.data.models.StashGeneral;

public interface StashGeneralDBUtil {

    /**
     * to load all shared db values at startup
     * @return
     */
    public StashGeneral loadGeneralObjectInstanceFromDB();
}
