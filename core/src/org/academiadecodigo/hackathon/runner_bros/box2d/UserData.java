package org.academiadecodigo.hackathon.runner_bros.box2d;

/**
 * Created by cadet on 30/10/15.
 */
public class UserData {

    protected UserDataType userDataType;

    public UserData(UserDataType userDataType) {
        this.userDataType = userDataType;
    }
    public void setUserDataType(UserDataType userDataType) {
        this.userDataType = userDataType;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

}
