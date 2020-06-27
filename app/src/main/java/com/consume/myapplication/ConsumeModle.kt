package com.consume.myapplication

import java.util.*

class ConsumeModle {
    var createTime: String? = null
    var inputType: String? = null
    var payType: String? = null
    var money: String? = null
    var note: String? = null
    var userName: String? = null
    constructor()
    constructor(
        createTime: String?,
        inputType: String?,
        payType: String?,
        money: String?,
        note: String?,
        userName: String?
    ) {
        this.createTime = createTime
        this.inputType = inputType
        this.payType = payType
        this.money = money
        this.note = note
        this.userName = userName
    }
}