Change Log
======

Here is all the changes that I made for make dungeon realm plugin work for 1.8.

- Developer: Li Shaodong
- Date: 
- Contact: lisdpku@gmail.com

#Maven Pom.xml
I have changed the pom.xml. Changed the version of SpigotAPI and Spigot to 1.8.3-R0.1-SNAPSHOT.

And add a repository 
    
	<repository>
        <id>spigot-repo</id>
        <url>https://hub.spigotmc.org/nexus/content/groups/public/</url>
	</repository>


#Chage on Code

##General Change

###On import
- Change ````net.minecraft.server.v1_7_R4```` to  ````net.minecraft.server.v1_8_R2````.
- Change ````org.bukkit.craftbukkit.v1_7_R4```` to  ````org.bukkit.craftbukkit.v1_8_R2````.
- Change ````net.minecraft.util.org.apache.commons.lang3.StringEscapeUtils```` to ````org.apache.commons.lang3.StringEscapeUtils```` and add corresponding library if necessary. The package ````net.minecraft.util```` have been totally removed.

###On Fileds
- If some fileds have change from ````public```` to ````protected```` or ````private```` and add corresponding ````getter()```` and ````setter()````, then just change it methods.

###Specific Change
#### Locked Chest
 ````Material.LOCKED_CHEST```` have been removed in 1.8. Just remove this if it appears in the code.
#### PacketPlayOutWorldEvent
The constructor ````new PacketPlayOutWorldEvent(int,int,int,int,int,boolean)```` now change to ````new PacketPlayOutWorldEvent(int,BlockPostion,boolean)````.

Use

	BlockPosition blockPostion = new BlockPosition(int2,int3,int4);
	new PacketPlayOutWorldEvent(int1,blockPosition,int5,boolean);

instead.


#### EntityTrackerEntry

In ````EntityTrackerEntry````, ````scanPlayers(List<EntityPlayer> list)```` change to ````scanPlayers(List<EntityHuman> list)````. This affect ````package minecade.dungeonrealms.InstanceMechanics.InstanceMechanics```` line 968.

#### Others
- ````package minecade.dungeonrealms.MonsterMechanics.CustomWolf```` line 21.
- MonsterMechanics 1069
- 
