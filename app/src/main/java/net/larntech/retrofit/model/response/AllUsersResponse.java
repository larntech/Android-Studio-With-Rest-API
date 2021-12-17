package net.larntech.retrofit.model.response;

import java.io.Serializable;
import java.util.List;

public class AllUsersResponse implements Serializable {


    /**
     * isSuccess : 1
     * message : login sucessful
     * users : [{"id":"1","username":"martin1","password":"827ccb0eea8a706c4c34a16891f84e7b","expiry":null},{"id":"2","username":"janet","password":"81dc9bdb52d04dc20036dbd8313ed055","expiry":null}]
     */

    private int isSuccess;
    private String message;
    /**
     * id : 1
     * username : martin1
     * password : 827ccb0eea8a706c4c34a16891f84e7b
     * expiry : null
     */

    private List<UsersBean> users;

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable {
        private String id;
        private String username;
        private String password;
        private String expiry;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }
    }
}
