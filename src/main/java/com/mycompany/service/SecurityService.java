package com.mycompany.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.joda.time.DateTime;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import com.mycompany.entity.AccessLog;
import com.mycompany.entity.User;
import com.mycompany.security.JpaUserDetails;
import com.mycompany.util.Util;

@Service
public class SecurityService {
	private final static UserAgentStringParser UAPARSER = UADetectorServiceFactory.getResourceModuleParser();

	@PersistenceContext
	private EntityManager entityManager;

	@ExtDirectMethod
	@PreAuthorize("isAuthenticated()")
	@Transactional
	public User getLoggedOnUser(HttpServletRequest request, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof JpaUserDetails) {

			User user = entityManager.find(User.class, ((JpaUserDetails) principal).getUserDbId());

			AccessLog accessLog = new AccessLog();
			accessLog.setUserName(user.getUserName());
			accessLog.setSessionId(session.getId());
			accessLog.setLogIn(DateTime.now());
			String ua = request.getHeader("User-Agent");
			if (StringUtils.hasText(ua)) {
				accessLog.setUserAgent(ua);
				ReadableUserAgent agent = UAPARSER.parse(ua);
				accessLog.setUserAgentName(agent.getName());
				accessLog.setUserAgentVersion(agent.getVersionNumber().getMajor());
				accessLog.setOperatingSystem(agent.getOperatingSystem().getFamilyName());
			}

			entityManager.persist(accessLog);

			return user;

		}
		return null;
	}

	@ExtDirectMethod
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public boolean switchUser(Long userId) {
		User switchToUser = entityManager.find(User.class, userId);
		if (switchToUser != null) {
			Util.signin(switchToUser);
			return true;
		}

		return false;
	}

}
