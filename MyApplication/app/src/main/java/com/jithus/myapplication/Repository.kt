package com.jithus.myapplication

/**
 * The class designed to do the database and network communication.
 * But for now, the communication to firebase server is not implemented and
 * It is using dummy value
 */
class Repository {
    /**
     * The method designed to authenticate admin and user.
     * But currently password auth is not implemented.
     */
    fun getAuth(username:String, password:String): String {
        var response: String;
        if(username.equals("admin", true)) {
            response = "owner";
        } else
            response = "user"
        return response;
    }
}