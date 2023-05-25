package com.andrei.game.util;


import java.sql.*;

public class ScoreBd {
    private static final String DB_URL = "jdbc:sqlite:res/BazaDate.db";
    private static final String USER = "guest";
    private static final String PASS = "guest123";
    public static int scoreLv1;
    public static int scoreLv2;
    public static int scoreLv3;

    public static void CitireScoruri() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Level1, Level2, Level3 FROM MaxScore");

            scoreLv1=rs.getInt(1);
            scoreLv2=rs.getInt(2);
            scoreLv3=rs.getInt(3);


            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void ScriereScor(int nivel, int scor){
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            System.out.println("S a scris");
            System.out.println(scor);

            switch(nivel){
                case 1 ->{
                    if(scor>scoreLv1)
                    {
                        String sql = "UPDATE MaxScore set Level1 = "+scor;
                        stmt.executeUpdate(sql);
                    }
                }
                case 2 ->{
                    if(scor>scoreLv2)
                    {
                        System.out.println("S a lc23");
                        String sql = "UPDATE MaxScore set Level2 = "+scor;
                        stmt.executeUpdate(sql);
                    }


                }
                case 3 ->{
                    if(scor>scoreLv3)
                    {
                        String sql = "UPDATE MaxScore set Level3 = "+scor;
                        stmt.executeUpdate(sql);
                    }
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

