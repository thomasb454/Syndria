package minecade.dungeonrealms.Hive;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import minecade.dungeonrealms.Main;
import minecade.dungeonrealms.MonsterMechanics.MonsterMechanics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum ParticleEffect {
	
	EXPLOSION_NORMAL("explode", 0),
	EXPLOSION_LARGE("largeexplode", 1),
	EXPLOSION_HUGE("hugeexplosion", 2),
	FIREWORKS_SPARK("fireworksSpark", 3),
	WATER_BUBBLE("bubble", 4),
	WATER_SPLASH("splash", 5),
	WATER_WAKE("wake", 6),
	SUSPENDED("suspended", 7),
	SUSPENDED_DEPTH("depthsuspend", 8),
	CRIT("crit", 9),
	CRIT_MAGIC("magicCrit", 10),
	SMOKE_NORMAL("smoke", 11),
	SMOKE_LARGE("largesmoke", 12),
	SPELL("spell", 13),
	SPELL_INSTANT("instantSpell", 14),
	SPELL_MOB("mobSpell", 15),
	SPELL_MOB_AMBIENT("mobSpellAmbient", 16),
	SPELL_WITCH("witchMagic", 17),
	DRIP_WATER("dripWater", 18),
	DRIP_LAVA("dripLava", 19),
	VILLAGER_ANGRY("angryVillager", 20),
	VILLAGER_HAPPY("happyVillager", 21),
	TOWN_AURA("townaura", 22),
	NOTE("note", 23),
	PORTAL("portal", 24),
	ENCHANTMENT_TABLE("enchantmenttable", 25),
	FLAME("flame", 26),
	LAVA("lava", 27),
	FOOTSTEP("footstep", 28),
	CLOUD("cloud", 29),
	REDSTONE("reddust", 30),
	SNOWBALL("snowballpoof", 31),
	SNOW_SHOVEL("snowshovel", 32),
	SLIME("slime", 33),
	HEART("heart", 34),
	BARRIER("barrier", 35),
	ITEM_CRACK("iconcrack_", 36),
	BLOCK_CRACK("blockcrack_", 37),
	BLOCK_DUST("blockdust_", 38),
	WATER_DROP("droplet", 39),
	ITEM_TAKE("take", 40),
	MOB_APPEARANCE("mobappearance", 41);
	

	private static Class<Enum> enumParticle = null;
	private String name;
	private int id;
	
	static{
		String className = "net.minecraft.server.v1_8_R2.EnumParticle";
		enumParticle = null;
		try {
			enumParticle = (Class<Enum>) Class.forName(className);
		}
		catch (ClassNotFoundException ex){
			ex.printStackTrace();
			Bukkit.getLogger().severe(" Failed to load NMS class " + "net.minecraft.server.v1_8_R2.EnumParticle" + "!");
		}
	}
	
	ParticleEffect(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	private static final Map<String, ParticleEffect> NAME_MAP = new HashMap<String, ParticleEffect>();
	private static final Map<Integer, ParticleEffect> ID_MAP = new HashMap<Integer, ParticleEffect>();
	static {
		for(ParticleEffect effect : values()) {
			NAME_MAP.put(effect.name, effect);
			ID_MAP.put(effect.id, effect);
		}
	}
	
	public static ParticleEffect fromName(String name) {
		if(name == null) { return null; }
		for(Entry<String, ParticleEffect> e : NAME_MAP.entrySet()) {
			if(e.getKey().equalsIgnoreCase(name)) { return e.getValue(); }
		}
		return null;
	}
	
	public static ParticleEffect fromId(int id) {
		return ID_MAP.get(id);
	}
	
	public static void sendToPlayer(ParticleEffect effect, Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
		Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
		sendPacket(player, packet);
	}
	
	public static void sendToLocation(final ParticleEffect effect, final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int count) throws Exception {
		// Hive.log.info(effect.name + " @ " + location.toString());
				Object packet = null;
				try {
					packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
				} catch(Exception e) {
					e.printStackTrace();
				}
				double radius = 32D;
				
				for(String s : MonsterMechanics.player_locations.keySet()) {
					if(Bukkit.getPlayerExact(s) != null) {
						Player pl = Main.plugin.getServer().getPlayer(s);
						if(pl.getWorld().getName().equalsIgnoreCase(location.getWorld().getName()) && pl.getLocation().toVector().distanceSquared(location.toVector()) <= Math.pow(radius, 2)) {
							try {
								sendPacket(pl, packet);
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
	}
	
	public static void sendCrackToPlayer(boolean icon, int id, byte data, Player player, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
		Object packet = createCrackPacket(icon, id, data, location, offsetX, offsetY, offsetZ, count);
		sendPacket(player, packet);
	}
	
	public static void sendCrackToLocation(boolean icon, int id, byte data, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
		Object packet = createCrackPacket(icon, id, data, location, offsetX, offsetY, offsetZ, count);
		for(Player player : Bukkit.getOnlinePlayers()) {
			sendPacket(player, packet);
		}
	}
	
	public static Object createPacket(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
		if(count <= 0) count = 1;
		Object packet = getPacket63WorldParticles();

		Object particleType = enumParticle.getEnumConstants()[effect.getId()];
		setValue(packet, "a", particleType);
		setValue(packet, "b", (float) location.getX());
		setValue(packet, "c", (float) location.getY());
		setValue(packet, "d", (float) location.getZ());
		setValue(packet, "e", offsetX);
		setValue(packet, "f", offsetY);
		setValue(packet, "g", offsetZ);
		setValue(packet, "h", speed);
		setValue(packet, "i", count);
		return packet;
	}
	
	public static Object createCrackPacket(boolean icon, int id, byte data, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
		if(count <= 0) count = 1;
		Object packet = getPacket63WorldParticles();
		String modifier = "iconcrack_" + id;
		if(!icon) {
			modifier = "tilecrack_" + id + "_" + data;
		}
		setValue(packet, "a", modifier);
		setValue(packet, "b", (float) location.getX());
		setValue(packet, "c", (float) location.getY());
		setValue(packet, "d", (float) location.getZ());
		setValue(packet, "e", offsetX);
		setValue(packet, "f", offsetY);
		setValue(packet, "g", offsetZ);
		setValue(packet, "h", 0.1F);
		setValue(packet, "i", count);
		return packet;
	}
	
	private static void setValue(Object instance, String fieldName, Object value) throws Exception {
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
	}
	
	private static Object getEntityPlayer(Player p) throws Exception {
		Method getHandle = p.getClass().getMethod("getHandle");
		return getHandle.invoke(p);
	}
	
	private static String getPackageName() {
		return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	}
	
	private static Object getPacket63WorldParticles() throws Exception {
		Class<?> packet = Class.forName(getPackageName() + ".PacketPlayOutWorldParticles");
		return packet.getConstructors()[0].newInstance();
	}
	
	private static void sendPacket(Player p, Object packet) throws Exception {
		Object eplayer = getEntityPlayer(p);
		Field playerConnectionField = eplayer.getClass().getField("playerConnection");
		Object playerConnection = playerConnectionField.get(eplayer);
		for(Method m : playerConnection.getClass().getMethods()) {
			if(m.getName().equalsIgnoreCase("sendPacket")) {
				m.invoke(playerConnection, packet);
				return;
			}
		}
	}
}
