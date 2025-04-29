package com.transcendence.freeland.ble.test2.utils;

import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dns;

/**
 * @author joephone
 * @date 2023/6/8
 * @desc
 */
public class BaiduDns implements Dns {

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {
                List<InetAddress> mInetAddressesList = new ArrayList<>();
                InetAddress[] mInetAddresses = InetAddress.getAllByName(hostname);
//                LogUtil.d("DNSAddress","ips:"+mInetAddresses.length);
                StringBuilder ips = new StringBuilder();
                try{
                    if(mInetAddresses!=null&&mInetAddresses.length>1){
                        for (InetAddress address : mInetAddresses) {
                            if (address instanceof Inet4Address) {
                                ips.append("IPV4===>"+address.getHostAddress()+","+address.getHostName()+","+address.getCanonicalHostName()+","+address.getCanonicalHostName()+"\n");
                            } else {
                                ips.append("IPV6===>"+address.getHostAddress()+","+address.getHostName()+","+address.getCanonicalHostName()+","+address.getCanonicalHostName()+"\n");
                            }
                        }
                        throw new IPException(ips.toString());
                    }else if(mInetAddresses!=null){
                        for (InetAddress address : mInetAddresses) {
                            if (!(address instanceof Inet4Address)) {
                                ips.append("IPV6===>"+address.getHostAddress()+","+address.getHostName()+","+address.getCanonicalHostName()+","+address.getCanonicalHostName()+"\n");
                            }
                            throw new IPException(ips.toString());
                        }
                    }

                }catch (IPException e){
                    Log.e("Freeland",e.getMessage());
                }

                for (InetAddress address : mInetAddresses) {
                    if (address instanceof Inet4Address) {
                        mInetAddressesList.add(0, address);
                    } else {
                        mInetAddressesList.add(address);
                    }
                }
                return mInetAddressesList;
            } catch (NullPointerException var4) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour");
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }
        }
    }

    public static class IPException extends Exception{
        public IPException(String message) {
            super(message);
        }
    }
}
