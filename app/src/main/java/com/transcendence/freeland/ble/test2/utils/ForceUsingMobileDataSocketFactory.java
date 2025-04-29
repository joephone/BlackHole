package com.transcendence.freeland.ble.test2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

/**
 * @author joephone
 * @date 2023/6/7
 * @desc
 */
public class ForceUsingMobileDataSocketFactory extends SocketFactory {

    private static final String TAG = "ForceUsingMobileDataSocketFactory";
    private Context context;

    public ForceUsingMobileDataSocketFactory(Context context) {
        this.context = context;
    }

    @Override
    public Socket createSocket() throws IOException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network mobileNetwork = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network[] networks = connectivityManager.getAllNetworks();
            for (Network network : networks) {
                if (connectivityManager.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_MOBILE) {
                    mobileNetwork = network;
                    break;
                }
            }
        }
        if (mobileNetwork == null) {
            Log.d(TAG, "createSocket no available mobile network");
            throw new IllegalStateException("no available mobile network");
        }
        Socket socket = mobileNetwork.getSocketFactory().createSocket();
        Log.d(TAG, "createSocket using mobile network: " + mobileNetwork.toString());
        return socket;
    }

    /**
     * Creates a socket and connects it to the specified remote host
     * at the specified remote port.  This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host the server host name with which to connect, or
     *             <code>null</code> for the loopback address.
     * @param port the server port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws UnknownHostException     if the host is not known
     * @throws IllegalArgumentException if the port parameter is outside the
     *                                  specified range of valid port values, which is between 0 and
     *                                  65535, inclusive.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(String, int)
     */
    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return null;
    }

    /**
     * Creates a socket and connects it to the specified remote host
     * on the specified remote port.
     * The socket will also be bound to the local address and port supplied.
     * This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host      the server host name with which to connect, or
     *                  <code>null</code> for the loopback address.
     * @param port      the server port
     * @param localHost the local address the socket is bound to
     * @param localPort the local port the socket is bound to
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws UnknownHostException     if the host is not known
     * @throws IllegalArgumentException if the port parameter or localPort
     *                                  parameter is outside the specified range of valid port values,
     *                                  which is between 0 and 65535, inclusive.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(String, int, InetAddress, int)
     */
    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return null;
    }

    /**
     * Creates a socket and connects it to the specified port number
     * at the specified address.  This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host the server host
     * @param port the server port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws IllegalArgumentException if the port parameter is outside the
     *                                  specified range of valid port values, which is between 0 and
     *                                  65535, inclusive.
     * @throws NullPointerException     if <code>host</code> is null.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return null;
    }

    /**
     * Creates a socket and connect it to the specified remote address
     * on the specified remote port.  The socket will also be bound
     * to the local address and port suplied.  The socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param address      the server network address
     * @param port         the server port
     * @param localAddress the client network address
     * @param localPort    the client port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws IllegalArgumentException if the port parameter or localPort
     *                                  parameter is outside the specified range of valid port values,
     *                                  which is between 0 and 65535, inclusive.
     * @throws NullPointerException     if <code>address</code> is null.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(InetAddress, int,
     * InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return null;
    }
}
