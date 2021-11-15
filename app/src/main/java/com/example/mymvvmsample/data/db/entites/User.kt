package com.example.mymvvmsample.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

/*{
  "isSuccessful": true,
  "message": "Login Successful",
  "user": {
    "id": "1",
    "name": "Belal Khan",
    "email": "probelalkhan@gmail.com",
    "email_verified_at": null,
    "created_at": "2019-06-05 05:13:11",
    "updated_at": "2019-06-05 05:13:11"
  }
}*/

const val CURRENT_USER_ID=0
@Entity(tableName = "user")
data class User(
    var id: Int? = null,
    var email: String? = null,
    var email_verified_at: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
){
    @PrimaryKey(autoGenerate = false)
    var uid:Int= CURRENT_USER_ID
}