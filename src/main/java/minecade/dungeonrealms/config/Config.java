package minecade.dungeonrealms.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;

public class Config {

	
	
	
	
	// Watcha doin here?
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// U sure u wanna be here?
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Well okay...
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public static List<String> us_public_servers = new ArrayList<String>(Arrays.asList("US-1", "US-2", "US-3", "US-4"));
    public static List<String> us_beta_servers = new ArrayList<String>(Arrays.asList("US-100"/*, "US-101", "US-102", "US-103", "US-104", "US-105", "US-106", "US-107", "US-108", "US-109", "US-110"*/));
    public static List<String> us_private_servers = new ArrayList<String>(Arrays.asList("US-5"));

    
    public static int transfer_port = 3306;
    public static String Hive_IP = "127.0.0.1";

    public static int SQL_port = 3306;
    public static String sql_user = "21730";
    public static String sql_password = "8b42ebc4ec";
    public static String sql_url = "jdbc:mysql://" + Hive_IP + ":" + SQL_port + "/21730";

    public static int FTP_port = 21;
    public static String ftp_user = "phyriasisasfuk";
    public static String ftp_pass = "dr4lyfe"; 
    
    public static String version = "1.9 BETA";
    
    public static String local_IP = Bukkit.getIp();
    
    public static String realmPath = "/rdata/";
}
