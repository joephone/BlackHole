package com.transcendence.greenstar.demo.encryption.act

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.transcendence.core.base.activity.AppAc
import com.transcendence.greenstar.R
import com.transcendence.greenstar.demo.encryption.lib.AES.AESUtil
import com.transcendence.greenstar.demo.encryption.lib.BASE64.Base64Util
import com.transcendence.greenstar.demo.encryption.lib.MD5.MD5Util
import com.transcendence.greenstar.demo.encryption.lib.RSA.RSAUtil
import com.transcendence.greenstar.demo.encryption.lib.SHA.SHAUtil
import com.transcendence.greenstar.demo.encryption.lib.SM2.SM2Util
import com.transcendence.greenstar.demo.encryption.lib.SM3.SM3Util
import com.transcendence.greenstar.demo.encryption.lib.SM4.SM4Util
import com.transcendence.greenstar.demo.encryption.utils.EncryptUtils
import java.security.KeyPair

/**
 * @author joephone
 * @date 2023/4/24
 * @desc
 */
class EncryptionAc : AppAc(),View.OnClickListener{

    private var type = 0
    private lateinit var titleString :String

    private lateinit var enEditText: EditText
    private lateinit var deEditText: EditText

    // AES
    private var keyAES = ByteArray(0)
    // RSA
    private var keyPairRSA: KeyPair? = null

    // SM2
    private lateinit var publicKeySM2:ByteArray  //= ByteArray(0)
    private lateinit var privateKeySM2:ByteArray //= ByteArray(0)

    // SM4
    private lateinit var keySM4:ByteArray //= ByteArray(0)

    //SM2 默认公钥
    private var publicKeySM2Str ="031e09b445c808d2b97ff01f318f785672fac17f03afc29c259b0548d7f3ad6c66"
    //SM2 默认私钥
    private var privateKeySM2Str ="00a3562aa22fee343b31ce90c0abc36cd7373bd231fbe754d0aeb02c471d48e7f2"
    //SM4 默认key
    private var keySM4Str = "1F37B36669F6531DE058A9323199594C"

    override fun getLayoutId(): Int {
        return R.layout.activity_demo_encryption_encrypt_decrypt
    }

    override fun initView() {
        type = intent.getIntExtra("type", 0)
        titleString = intent.getStringExtra("name").toString()    //设置标题

        val btn_de: Button = findViewById(R.id.btn_de)
        enEditText = findViewById(R.id.et_top)
        deEditText = findViewById(R.id.et_bottom)
        btn_de.visibility = View.VISIBLE
        setTitle(titleString)

        // 根据类型，先生成密钥
        when (type) {
            0 -> {
                keyAES = AESUtil.generateKey()
            }
            2, 4, 6 -> {
                btn_de.visibility = View.GONE
            }
            3 -> {
                keyPairRSA = RSAUtil.generateRSAKeyPair()
            }
            5 -> {
//                val key = SM2Util.generateKeyPair()
//                publicKeySM2 = key[0]
//                privateKeySM2 = key[1]
                publicKeySM2 = EncryptUtils.hexStr2Bytes(publicKeySM2Str)
                privateKeySM2 = EncryptUtils.hexStr2Bytes(privateKeySM2Str)
            }
            7 -> {
//                keySM4 = SM4Util.createSM4Key()
                keySM4 = EncryptUtils.hexStr2Bytes(keySM4Str)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_en -> {
                val enString = enEditText.text.trim().toString()
                if (enString.isEmpty()) {
                    showMsg("请输入待加密数据")
                    return
                }
                val manager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                encrypt(enString)
            }
            R.id.btn_de -> {
                val deString = deEditText.text.trim().toString()
                if (deString.isEmpty()) {
                    showMsg("请输入待解密数据")
                    return
                }
                val manager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                decrypt(deString)
            }
        }
    }

    private fun encrypt(dataString: String) {
//        LogUtils.d("dataString------------------"+dataString)
//        LogUtils.d("publicKeySM2------------------"+publicKeySM2)
//        LogUtils.d("privateKeySM2------------------"+privateKeySM2)
        val enString = when (type) {
            0 -> {
                EncryptUtils.byte2HexStr(AESUtil.encrypt(dataString.toByteArray(), keyAES))
            }
            1 -> {
                Base64Util.encode(dataString.toByteArray())
            }
            2 -> {
                MD5Util.stringMD5(dataString)
            }
            3 -> {
                EncryptUtils.byte2HexStr(RSAUtil.encryptByPublicKey(dataString.toByteArray(), keyPairRSA?.public))
            }
            4 -> {
                SHAUtil.stringSHA(dataString, SHAUtil.SHA1)
            }
            5 -> {
                EncryptUtils.byte2HexStr(SM2Util.encrypt(publicKeySM2, dataString.toByteArray()))
            }
            6 -> {
                SM3Util.encryptInner(dataString)
            }
            7 -> {
                EncryptUtils.byte2HexStr(SM4Util.encrypt(dataString.toByteArray(), keySM4, SM4Util.SM4_CBC_PKCS5, ByteArray(16)))
            }
            else -> ""
        }
        deEditText.setText(enString)
        enEditText.setText("")
        showMsg("加密完成")
    }

    private fun decrypt(dataString: String) {
        val deString = when (type) {
            0 -> {
                String(AESUtil.decrypt(EncryptUtils.hexStr2Bytes(dataString), keyAES))
            }
            1 -> {
                String(Base64Util.decode(dataString))
            }
            3 -> {
                String(RSAUtil.decryptByPrivateKey(EncryptUtils.hexStr2Bytes(dataString), keyPairRSA?.private))
            }
            5 -> {
                String(SM2Util.decrypt(privateKeySM2, EncryptUtils.hexStr2Bytes(dataString)))
            }
            7 -> {
                String(SM4Util.decrypt(EncryptUtils.hexStr2Bytes(dataString), keySM4, SM4Util.SM4_CBC_PKCS5, ByteArray(16)))
            }
            else -> ""
        }
        enEditText.setText(deString)
        deEditText.setText("")
        showMsg("解密完成")
    }
}