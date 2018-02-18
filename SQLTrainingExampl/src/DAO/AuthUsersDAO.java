/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Logic.Authusers;
import Logic.Users;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface AuthUsersDAO {
    
    public void Add(Authusers aUsers);
    public Collection getAllAuthUser();
    public void Delete(Integer ID);
    public Collection findByMAC(String MacA);
    
}
