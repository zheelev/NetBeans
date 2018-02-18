/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Logic.Authusers;
import Logic.Users;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Admin
 */
public interface UsersDAO {
    
    public void Add(Users users) throws SQLException;
    public void UpdateUser(Users users);
    public void deleteUser(Users users);
    public Collection getUser(String username, String password) throws SQLException;
    public Collection getAllUser() throws SQLException;
    public Users findByID(Integer ID_users)throws SQLException;
    public Collection getAuthUserInfo(Authusers user);
    public Collection findUserByUserName(String username) throws SQLException;
    
}
