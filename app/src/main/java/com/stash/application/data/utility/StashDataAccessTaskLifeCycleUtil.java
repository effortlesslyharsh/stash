package com.stash.application.data.utility;


import com.stash.application.constants.StashConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * class to controll the life cycle of data loading
 */
@Getter
@Setter
public  class StashDataAccessTaskLifeCycleUtil {
    private boolean isTaskCompleted = false;
    private long taskStartTime;
    private long taskEndTime;
    private final long timeOutThreshold = StashConstants.DB_FETCH_TIMEOUT_MILLISECONDS;
}
