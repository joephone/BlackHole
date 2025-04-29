package com.transcendence.freeland.basefun.synch;

import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.freeland.R;

/**
 * @author joephone
 * @date 2025/4/16 4:06
 * @description
 * @edition 1.0
 */
public class SynchActivity extends AppAc {
    private Account account = new Account();
    private TextView tvBalance;
    private TextView tvLog;
    private Switch switchLock;
    private Button btnWithdraw800;
    private Button btnWithdraw300;


    private void updateBalanceUI() {
        tvBalance.setText("余额：" + account.getBalance() + "元");
    }

    private void addLog(String message) {
        tvLog.append("\n" + message);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_basefun_synchronized;
    }

    @Override
    protected void initView() {
        tvBalance = findViewById(R.id.tvBalance);
        tvLog = findViewById(R.id.tvLog);
        switchLock = findViewById(R.id.switchLock);
        btnWithdraw800 = findViewById(R.id.btnWithdraw800);
        btnWithdraw300 = findViewById(R.id.btnWithdraw300);

        updateBalanceUI();

        btnWithdraw800.setOnClickListener(v-> {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (switchLock.isChecked()) {
                            account.withdrawWithLock(800);
                        } else {
                            account.withdrawWithoutLock(800);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateBalanceUI();
                                addLog("线程A尝试取800");
                            }
                        });
                    }
                }).start();

        });

        btnWithdraw300.setOnClickListener(v->  {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (switchLock.isChecked()) {
                            account.withdrawWithLock(300);
                        } else {
                            account.withdrawWithoutLock(300);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateBalanceUI();
                                addLog("线程B尝试取300");
                            }
                        });
                    }
                }).start();

        });
    }

    class Account {
        private int balance = 1000;
        private final Object lock = new Object();

        public int getBalance() {
            return balance;
        }

        // 无锁的取款方法
        public void withdrawWithoutLock(int amount) {
            if (balance >= amount) {
                try {
                    Thread.sleep(100); // 模拟处理延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
            }
        }

        // 加锁的取款方法
        public void withdrawWithLock(int amount) {
            synchronized (lock) {
                if (balance >= amount) {
                    try {
                        Thread.sleep(100); // 模拟处理延迟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    balance -= amount;
                }
            }
        }
    }
}