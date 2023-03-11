package com.tibame.tga105.user.ultility;

import javax.servlet.http.HttpServletRequest;

public class SiteUrl {
	public static String getSiteURL(HttpServletRequest req) {
		String siteUrl = req.getRequestURL().toString();
		return siteUrl.replace(req.getServletPath(), "");
	}
}
