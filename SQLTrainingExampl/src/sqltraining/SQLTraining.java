/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltraining;

import DAO.Factory;
import Logic.Authusers;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SQLTraining {
    
    static ArrayList<String> list = new ArrayList<String>();
    public void SQLTraining()
    {
       
    }
    public static String Mac()
        {
        String macA = null;
        try {
            InetAddress ip;
            
            ip = InetAddress.getLocalHost();
            NetworkInterface network;
            
            try {
                network = NetworkInterface.getByInetAddress(ip);
                byte[] mac = network.getHardwareAddress();
            
                //System.out.print("Current MAC address : ");

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                macA = sb.toString();
            } catch (SocketException ex) {
                Logger.getLogger(SQLTraining.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (UnknownHostException ex) {
            Logger.getLogger(SQLTraining.class.getName()).log(Level.SEVERE, null, ex);
        }
        return macA;
           
        }
    
    public static void main(String args[]) {
        String mac = Mac();
        list.clear();
        Collection aUser = Factory.getInstance().getAuthUserDAO().getAllAuthUser();
        if(aUser.isEmpty())
        {
            Authoruzation au = new Authoruzation();
            au.setVisible(true);
        }
        else 
        {
            Boolean status =  false;
            Iterator iterator = aUser.iterator();

            while (iterator.hasNext()) {
                Authusers aUsers = (Authusers) iterator.next();
                status = aUsers.getMac().contains(mac);
                System.out.println(status);
                if(status == true)
                {
                    list.add("1");
                }
               }
                 if(list.isEmpty())
                    {
                        Authoruzation au = new Authoruzation();
                        au.setVisible(true);
                    }
                else
                    {
                        MainMenu mM = new MainMenu();
                        mM.setVisible(true);

                    }

        }
    }
}
