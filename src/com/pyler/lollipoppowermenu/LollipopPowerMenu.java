package com.pyler.lollipoppowermenu;

import java.util.ArrayList;

import android.content.res.XResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;

public class LollipopPowerMenu implements IXposedHookZygoteInit {
	public static final String GLOBAL_ACTION_KEY_POWER = "power";
	public static final String GLOBAL_ACTION_KEY_AIRPLANE = "airplane";
	public static final String GLOBAL_ACTION_KEY_SILENT = "silent";
	public static final String GLOBAL_ACTION_KEY_USERS = "users";
	public static final String GLOBAL_ACTION_KEY_SETTINGS = "settings";
	public static final String GLOBAL_ACTION_KEY_LOCKDOWN = "lockdown";
	public XSharedPreferences prefs;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		prefs = new XSharedPreferences(LollipopPowerMenu.class.getPackage()
				.getName());
		prefs.makeWorldReadable();
		ArrayList<String> itemsList = new ArrayList<String>();
		if (isEnabled(GLOBAL_ACTION_KEY_POWER)) {
			itemsList.add(GLOBAL_ACTION_KEY_POWER);
		}
		if (isEnabled(GLOBAL_ACTION_KEY_AIRPLANE)) {
			itemsList.add(GLOBAL_ACTION_KEY_AIRPLANE);
		}
		if (isEnabled(GLOBAL_ACTION_KEY_SILENT)) {
			itemsList.add(GLOBAL_ACTION_KEY_SILENT);
		}
		if (isEnabled(GLOBAL_ACTION_KEY_USERS)) {
			itemsList.add(GLOBAL_ACTION_KEY_USERS);
		}
		if (isEnabled(GLOBAL_ACTION_KEY_SETTINGS)) {
			itemsList.add(GLOBAL_ACTION_KEY_SETTINGS);
		}
		if (isEnabled(GLOBAL_ACTION_KEY_LOCKDOWN)) {
			itemsList.add(GLOBAL_ACTION_KEY_LOCKDOWN);
		}
		String[] powerMenuItems = itemsList
				.toArray(new String[itemsList.size()]);
		XResources.setSystemWideReplacement("android", "array",
				"config_globalActionsList", powerMenuItems);
	}

	public boolean isEnabled(String preference) {
		return prefs.getBoolean(preference, false);
	}
}