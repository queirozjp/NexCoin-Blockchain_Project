package com.nexcoin.util;

import com.nexcoin.entities.Wallet;
// Check the existence of the wallet to return wich wallet it is 
public class WalletValidation {
    private Wallet wallet;
    private boolean exists;

    public WalletValidation(Wallet wallet, boolean exists){
        this.wallet = wallet;
        this.exists = exists;
    }

    public Wallet getWallet(){
        return wallet;
    }
    public boolean getExist(){
        return exists;
    }

}
