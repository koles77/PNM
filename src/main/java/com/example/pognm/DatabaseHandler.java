package com.example.pognm;
import java.sql.*;
import java.util.ArrayList;


public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void addArea(String nameArea, String areaNote) {
        String insert = "INSERT INTO " + Const.AREA_TABLE + "(" + Const.AREA_NAMEAREA + "," + Const.AREA_NOTE + ")" + "VALUES(?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, nameArea);
            prSt.setString(2, areaNote);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAreas() {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.AREA_TABLE + " WHERE " + Const.AREA_NAMEAREA + " IS NOT NULL";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet countOfId() {
        ResultSet rs = null;
        String select = "SELECT COUNT(id) FROM " + Const.AREA_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            rs = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void addPc(String pcLoc, String pcName, String pcIp, String pcLogo, String pcPswd) {
        String insert = "INSERT INTO " + Const.PC_TABLE + "(" + Const.PC_LOC + "," + Const.PC_NAME + "," + Const.PC_IP + "," + Const.PC_LOG + "," + Const.PC_PSWD + ")" + "VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, pcLoc);
            prSt.setString(2, pcName);
            prSt.setString(3, pcIp);
            prSt.setString(4, pcLogo);
            prSt.setString(5, pcPswd);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPc(String nameOfArea) {
        ResultSet resSet = null;
        // подготовить подходящее выражение для выборки по нажатию кнопки с id
        String select = "SELECT * FROM " + Const.PC_TABLE + " WHERE " + Const.PC_LOC + " = " + "'" + nameOfArea + "'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    // Доработать dbh
    public ArrayList<String> getInfoPcById(Integer id) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.PC_TABLE + " WHERE " + Const.PC_ID + " = " + id;
        ArrayList<String> listOfData = new ArrayList<String>();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            resSet.first();
            listOfData.add(resSet.getString(2));
            listOfData.add(resSet.getString(3));
            listOfData.add(resSet.getString(4));
            listOfData.add(resSet.getString(5));
            listOfData.add(resSet.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listOfData;
    }

    public boolean removePcFromDB(String nameOfArea) {
        boolean resSet = Boolean.parseBoolean(null);
        // подготовить подходящее выражение для выборки по нажатию кнопки с id
        String select = "DELETE FROM " + Const.PC_TABLE + " WHERE " + Const.PC_LOC + " = " + "'" + nameOfArea + "'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public boolean removeAreaFromDB(String nameOfArea) {
        boolean resSet = Boolean.parseBoolean(null);
        // подготовить подходящее выражение для выборки по нажатию кнопки с id
        String select = "DELETE FROM " + Const.AREA_TABLE + " WHERE " + Const.AREA_NAMEAREA + " = " + "'" + nameOfArea + "'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public boolean removePcByIdFromDB(Integer idOfPC) {
        boolean resSet = Boolean.parseBoolean(null);
        // подготовить подходящее выражение для выборки по нажатию кнопки с id
        String select = "DELETE FROM " + Const.PC_TABLE + " WHERE " + Const.PC_ID + " = " + idOfPC;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public boolean updatePcByIdInDB(Integer idOfPC, String name, String ip, String login, String password) {
        boolean resSet = Boolean.parseBoolean(null);
        // подготовить подходящее выражение для выборки по нажатию кнопки с id
        String select = "UPDATE " + Const.PC_TABLE + " SET " + Const.PC_NAME + " = '" + name + "', " +
                Const.PC_IP + " = '" + ip + "', " + Const.PC_LOG + " = '" + login + "', " + Const.PC_PSWD + " = '" + password +
                "' WHERE " + Const.PC_ID + " = " + idOfPC;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
