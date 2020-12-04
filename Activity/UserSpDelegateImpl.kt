package com.example.foodbuddy

object User : SharedPreferencesUtils.Delegates() {

    override fun getSharedPreferencesName(): String = User.javaClass.simpleName

    var isLogin by boolean()
    var user by string()
    var token by string()
    var userName by string()
    var fName by string()
    var lName by string()
    var email by string()
    var pwd by string()
}