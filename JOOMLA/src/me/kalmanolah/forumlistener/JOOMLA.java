package me.kalmanolah.forumlistener;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import me.kalmanolah.extras.Tools;
import me.kalmanolah.okb3.OKBSync;
import me.kalmanolah.okb3.OKConfig;
import me.kalmanolah.okb3.OKDatabase;
import me.kalmanolah.okb3.OKLogger;

public class JOOMLA implements OKBSync {

public JOOMLA() {
// TODO Auto-generated constructor stub
}

@Override
public boolean accountExist(String username, String password) {
    String encpass = "";
    boolean exist = false;
    try {
    ResultSet rs =
    OKDatabase.dbm.prepare(
    "SELECT password FROM " + OKConfig.config.get("db.prefix")
    + "users WHERE username = '" + username + "'").executeQuery();
    if (rs.next()) {
    do {
    encpass = Tools.md5(Tools.md5(password));
    if (encpass.equals(rs.getString("password"))) {
    exist = true;
    }
    } while (rs.next());
    }
    rs.close();
    } catch (SQLException e) {
    e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
    e.printStackTrace();
    }
    return exist;
}

@Override
public void changeRank(String username, int forumGroupId) {
    try {
                 Object map = OKConfig.config.get("groups");
                 HashMap<Integer, String> groupmap = null;
                 if(map instanceof HashMap){
                     groupmap = (HashMap)map;
                 }
                
                String rank = groupmap.get(forumGroupId);
                if(groupmap != null){
                    OKDatabase.dbm.prepare("UPDATE " + OKConfig.config.get("db.prefix") + "users SET usertype=" + rank + " WHERE username = '" + username + "'").executeUpdate();
                }
                else{
                    OKLogger.info("[OKB3] Fail at line: '52-54' Contact Somers and tell him he failed.");
                }
    } catch (SQLException e) {
    e.printStackTrace();
    }
}

@Override
public void ban(String username, int forumGroupId) {
    // TODO use vBulletin ban system
    changeRank(username,forumGroupId);
}

@Override
public void unban(String username, int forumGroupId) {
    // TODO use vBulletin ban system
    changeRank(username,forumGroupId);
}

@Override
public List<Integer> getGroup(String username) {
    List<Integer> group = new ArrayList<Integer>();
    try
    {
    ResultSet rs = OKDatabase.dbm.prepare("SELECT usertype FROM " + OKConfig.config.get("db.prefix") + "users WHERE username = '" + username + "'").executeQuery();
    if (rs.next())
    {
    do
    {
    group.add(rs.getInt("usergroupid"));
    }
    while(rs.next());
    }
    rs.close();
    rs = OKDatabase.dbm.prepare("SELECT membergroupids FROM " + OKConfig.config.get("db.prefix") + "users WHERE username = '" + username + "'").executeQuery();
    if (rs.next())
    {
    do
    {
    group.add(rs.getInt("membergroupids"));
    }
    while(rs.next());
    }
    rs.close();
    }
    catch (SQLException e)
            {
                e.printStackTrace();
            }

    return group;
}

}