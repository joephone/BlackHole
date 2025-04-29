package com.transcendence.greenstar.demo.encryption.act

import android.content.Intent
import android.view.View
import com.transcendence.core.base.activity.AppAc
import com.transcendence.greenstar.R

/**
 * @author joephone
 * @date 2023/4/24
 * @desc
 */
class EncryptionMainAc : AppAc(),View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_demo_encryption_main
    }

    override fun initView() {
        setTitle("Encryption")

    }

    override fun onClick(v : View?) {
        val intent = Intent(this,EncryptionAc::class.java)
        var type = 0
        var name = "AES"
        when (v?.id){
            R.id.btn_aes -> {
                type = 0
                name = "AES"
            }
            R.id.btn_base64 -> {
                type = 1
                name = "BASE64"
            }
            R.id.btn_md5 -> {
                type = 2
                name = "MD5"
            }
            R.id.btn_rsa -> {
                type = 3
                name = "RSA"
            }
            R.id.btn_sha -> {
                type = 4
                name = "SHA"
            }
            R.id.btn_sm2 -> {
                type = 5
                name = "SM2"
            }
            R.id.btn_sm3 -> {
                type = 6
                name = "SM3"
            }
            R.id.btn_sm4 -> {
                type = 7
                name = "SM4"
            }
        }
        intent.putExtra("type",type)
        intent.putExtra("name",name)
        startActivity(intent)
    }


}